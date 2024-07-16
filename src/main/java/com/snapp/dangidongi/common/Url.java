package com.snapp.dangidongi.common;

public interface Url {

    String API_V1 = "/api/v1";

    String USERS = API_V1 + "/users";
    String USERS_ID = API_V1 + "/users/{id}";
    String USERS_PHONE = API_V1 + "/users/phone/{phone}";


    String FRIEND_GROUP = API_V1 + "/friend-groups";
    String FRIEND_GROUP_ID = API_V1 + "/friend-groups/{id}";
    String FRIEND_GROUP_CREATOR_ID = API_V1 + "/friend-groups/creator/{creator-id}";
    String FRIEND_GROUP_ID_USERS = API_V1 + "/friend-groups/{group-id}/users/{user-id}";
}
