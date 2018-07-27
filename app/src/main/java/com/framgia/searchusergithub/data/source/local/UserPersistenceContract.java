package com.framgia.searchusergithub.data.source.local;

import android.provider.BaseColumns;

public final class UserPersistenceContract {

    public UserPersistenceContract() {
    }

    public static final class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_ENTRY_ID = "id";
        public static final String COLUMN_ENTRY_NAME = "id";
        public static final String COLUMN_ENTRY_URL = "url";
        public static final String COLUMN_ENTRY_AVATAR = "avatar";

    }


}
