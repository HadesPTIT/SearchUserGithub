package com.framgia.searchusergithub.data.source.remote;

import android.os.AsyncTask;

import com.framgia.searchusergithub.data.UserDataSource;
import com.framgia.searchusergithub.data.model.GitResponse;
import com.framgia.searchusergithub.data.model.User;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class UserRemoteDataSource extends UserDataSource {

    private static UserRemoteDataSource mInstance;
    private static final String BASE_URL = "https://api.github.com/search/users?per_page=X&q=Y";
    private static final String REPRESENT_LIMIT = "X";
    private static final String REPRESENT_KEY_WORD = "Y";

    public static synchronized UserRemoteDataSource getInstance() {
        if (mInstance == null) {
            mInstance = new UserRemoteDataSource();
        }
        return mInstance;
    }

    @Override
    public void getUsers(String keyword, int limit, GetUserCallback callback) {
        String apiString = BASE_URL.replace(REPRESENT_KEY_WORD, keyword)
                .replace(REPRESENT_LIMIT, String.valueOf(limit));
        new FetchDataFromUrl(callback).execute(apiString);
    }


    static class FetchDataFromUrl extends AsyncTask<String, Void, List<User>> {

        private GetUserCallback mCallback;
        private static String REQUEST_METHOD = "GET";
        private static int CONNECT_TIMEOUT = 10000;
        private static int READ_TIMEOUT = 15000;
        private static String SYM_ENDLINE = "\n";


        public FetchDataFromUrl(GetUserCallback callback) {
            mCallback = callback;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<User> doInBackground(String... strings) {
            try {
                String json = getJsonFromUrl(strings[0]);
                GitResponse usersList = new Gson().fromJson(json, GitResponse.class);
                return usersList.getUsers();
            } catch (IOException e) {
                e.printStackTrace();
                mCallback.onFailed(e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<User> users) {
            super.onPostExecute(users);
            mCallback.onSuccess(users);
        }

        private String getJsonFromUrl(String urlString) throws IOException {

            HttpURLConnection urlConnection = null;

            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod(REQUEST_METHOD);
            urlConnection.setReadTimeout(READ_TIMEOUT);
            urlConnection.setConnectTimeout(CONNECT_TIMEOUT);

            urlConnection.setDoOutput(true);
            urlConnection.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append(SYM_ENDLINE);
            }
            br.close();

            String jsonString = sb.toString();
            urlConnection.disconnect();

            return jsonString;
        }

    }

}
