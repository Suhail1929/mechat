package com.asynctaskcoffee.ui.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asynctaskcoffee.MainApplication;
import com.asynctaskcoffee.ui.R;
import com.asynctaskcoffee.ui.adapter.ChatAdapter;
import com.asynctaskcoffee.ui.db.DatabaseClient;
import com.asynctaskcoffee.ui.db.dao.ChatDao;
import com.asynctaskcoffee.ui.db.table.Chat;
import com.asynctaskcoffee.ui.db.table.Customer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;


public abstract class DbBaseActivity extends AppCompatActivity implements ChatAdapter.ItemClickListener {


    private ProgressDialog pd;
    private List chatList = new ArrayList<Chat>();

    private ChatAdapter adapter;

    @Nullable
    public final ProgressDialog getPd() {
        return this.pd;
    }

    public final void setPd(@Nullable ProgressDialog var1) {
        this.pd = var1;
    }

    public final void getDialog() {
        pd = new ProgressDialog(this);
    }

    public  void showDialog(@NotNull String msg) {
        pd.setMessage(msg);
        pd.show();
    }

    public void hideDialog() {
        pd.hide();
    }

    @Nullable
    public final ChatAdapter getAdapter() {
        return this.adapter;
    }

    public final void setAdapter(@Nullable ChatAdapter var1) {
        this.adapter = var1;
    }

    public final void setUpRv() {
        adapter = new ChatAdapter(this, chatList, getIntent());
        adapter.setClickListener(this);
        RecyclerView rv = findViewById(R.id.rv_chat);
        rv.setLayoutManager((new LinearLayoutManager(this, RecyclerView.VERTICAL, true)));
        rv.setAdapter(adapter);
    }

    public void startActivity(@NotNull Activity from, @Nullable Class to) {
        Intent intent = new Intent(from, to);
        from.startActivity(intent);
        from.overridePendingTransition(R.anim.enter_activity, R.anim.hold_activity);
    }

    public void startActivityWithNoHistory(@NotNull Activity from, @Nullable Class to) {
        Intent intent = new Intent(from, to);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        from.startActivity(intent);
        from.overridePendingTransition(R.anim.enter_activity, R.anim.hold_activity);
    }

    public void startActivity(@NotNull Activity from, @Nullable Intent intent) {
        from.startActivity(intent);
        from.overridePendingTransition(R.anim.enter_activity, R.anim.hold_activity);
    }

    public final void gotoMainActivity() {
        this.startActivityWithNoHistory(this, ChatMainActivity.class);
    }

    public static final class getAllchat extends AsyncTask<Void, Void, Integer> {
        @Nullable
        private final DbBaseActivity activity;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Nullable
        @Override
        protected Integer doInBackground(@NotNull Void... params) {

            DatabaseClient dbClient = DatabaseClient.getInstance(MainApplication.getInstance());
            ChatDao chatDao = dbClient.getAppDatabase().chatDao();
            List chatList = chatDao.getAllChat();
            activity.chatList = chatList;
            return chatList.size();
        }

        @Override
        protected void onPostExecute(Integer bookingCount) {
            super.onPostExecute(bookingCount);
            activity.adapter.updateChat(activity.chatList);
            if (bookingCount == 0) {
                Toast.makeText(activity, "pas de message trouvé", Toast.LENGTH_LONG).show();
            }

        }

        public getAllchat(@Nullable DbBaseActivity activity) {
            this.activity = activity;
        }
    }

    public static final class addChatToDb extends AsyncTask<Chat, Void, Chat> {

        @Nullable
        private final DbBaseActivity activity;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Nullable
        @Override
        protected Chat doInBackground(@NotNull Chat... params) {
            Chat chat = params[0];
            DatabaseClient dbClient = DatabaseClient.getInstance(MainApplication.getInstance());
            dbClient.getAppDatabase().chatDao().insert(chat);
            return chat;
        }

        @Override
        protected void onPostExecute(@Nullable Chat chat) {
            super.onPostExecute(chat);
            if (chat != null) {
                activity.chatList.add(chat);
                activity.adapter.updateChat(activity.chatList);
            }

        }

        public addChatToDb(@Nullable DbBaseActivity activity) {
            this.activity = activity;
        }
    }

    public static final class login extends AsyncTask<Customer, Void, Object> {

        @Nullable
        private final LoginActivity activity;

        @Override
        protected Object doInBackground(Customer[] params) {
            Customer customer = params[0];
            DatabaseClient dbClient = DatabaseClient.getInstance(MainApplication.getInstance());
            Customer customerObj = dbClient.getAppDatabase().customerDao().loginUser(customer != null ? customer.getEmail() : null, customer != null ? customer.getPassword() : null);
            return customerObj;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            activity.showDialog("Chargement...");
        }

        @Override
        protected void onPostExecute(@Nullable Object customerObj) {
            super.onPostExecute(customerObj);
            activity.hideDialog();
            if (customerObj != null) {
                activity.gotoMainActivity();
                Toast.makeText((Context) this.activity, (CharSequence) "Connexion réussie", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText((Context) this.activity, (CharSequence) "Email ou mot de passe incorrecte", Toast.LENGTH_LONG).show();
            }

        }

        public login(@Nullable LoginActivity activity) {
            this.activity = activity;
        }
    }

    public static final class Signup extends AsyncTask<Customer, Void, Object> {
        @Nullable
        private final SignupActivity activity;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            activity.showDialog("Chargement...");
        }

        @Nullable
        @Override
        protected Customer doInBackground(@NotNull Customer... params) {
            Customer customer = params[0];
            DatabaseClient dbClient = DatabaseClient.getInstance(MainApplication.getInstance());
            dbClient.getAppDatabase().customerDao().insert(customer);
            Unit result = Unit.INSTANCE;
            return customer;
        }

        @Override
        protected void onPostExecute(@Nullable Object customerObj) {
            super.onPostExecute(customerObj);
            activity.hideDialog();

            if (customerObj != null) {
                activity.gotoMainActivity();
                Toast.makeText(
                        MainApplication.getInstance(),
                        "Inscription réussie ",
                        Toast.LENGTH_LONG
                )
                        .show();
            } else {
                Toast.makeText(
                        MainApplication.getInstance(),
                        "Inscription echouée ",
                        Toast.LENGTH_LONG
                )
                        .show();
            }

        }

        public Signup(@Nullable SignupActivity activity) {
            this.activity = activity;
        }
    }
}

