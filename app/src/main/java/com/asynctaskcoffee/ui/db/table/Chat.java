package com.asynctaskcoffee.ui.db.table;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

@Entity(
        tableName = "chat"
)
public final class Chat implements Serializable {
    @PrimaryKey(
            autoGenerate = true
    )
    private int messageId;
    @ColumnInfo(
            name = "message"
    )
    @Nullable
    private String message;
    @ColumnInfo(
            name = "path"
    )
    @Nullable
    private String path;
    @ColumnInfo(
            name = "message_type"
    )
    @Nullable
    private String messageType;
    @ColumnInfo(
            name = "time"
    )
    @Nullable
    private String time;
    @ColumnInfo(
            name = "isPlaying"
    )
    private boolean isPlaying;

    public final int getMessageId() {
        return this.messageId;
    }

    public final void setMessageId(int var1) {
        this.messageId = var1;
    }

    @Nullable
    public final String getMessage() {
        return this.message;
    }

    public final void setMessage(@Nullable String var1) {
        this.message = var1;
    }

    @Nullable
    public final String getPath() {
        return this.path;
    }

    public final void setPath(@Nullable String var1) {
        this.path = var1;
    }

    @Nullable
    public final String getMessageType() {
        return this.messageType;
    }

    public final void setMessageType(@Nullable String var1) {
        this.messageType = var1;
    }

    @Nullable
    public final String getTime() {
        return this.time;
    }

    public final void setTime(@Nullable String var1) {
        this.time = var1;
    }

    public final boolean isPlaying() {
        return this.isPlaying;
    }

    public final void setPlaying(boolean var1) {
        this.isPlaying = var1;
    }
}