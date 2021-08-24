package com.zy.logger;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @ProjectName: FrameworkApp
 * @Package: com.zy.logger
 * @ClassName: LoggerContentProvider
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/8/21 9:56
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/8/21 9:56
 * @UpdateRemark:
 * @Version: 1.0
 */
public class LoggerInstaller extends ContentProvider
{
    private Context mContext=null;
    @Override
    public boolean onCreate() {
        mContext = getContext();

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
