package com.dentistry.ahmed.orderonline.FireBase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

public class MyFireBase {

    private static FirebaseAuth auth;
    private static FirebaseDatabase FirebaseDatabase;
    private static FirebaseStorage firebaseStorage;
    private static DatabaseReference referenceOnAllUsers;
    private static DatabaseReference referenceOnItem;
    private static DatabaseReference referenceOnDeals;
    private static DatabaseReference referenceOnColors;
    private static DatabaseReference referenceOnOrders;
    private static DatabaseReference referenceOnCurrentUser;
    private static DatabaseReference referenceOnDataBase;
    private static DatabaseReference referenceOnChats;
    private static DatabaseReference referenceOnPhotos;
    private static DatabaseReference referenceOnChatList;
    private static DatabaseReference referenceOnFollowings;
    private static FirebaseUser currentUser;
    private static StorageReference storageReferenceOnUploads;
    private static StorageReference storageReferenceOnPhotos;


    private static DatabaseReference yourNewReference;

    public static FirebaseAuth getAuth() {

        if (auth == null) {
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }

    private static FirebaseDatabase getGetFirebaseDatabase() {

        if (FirebaseDatabase == null){
            FirebaseDatabase = FirebaseDatabase.getInstance();
        }
        return FirebaseDatabase;
    }

    public static FirebaseStorage getFirebaseStorage() {

        if (firebaseStorage == null) {
            firebaseStorage = FirebaseStorage.getInstance();
        }

        return firebaseStorage;
    }

    public static StorageReference getStorageReferenceOnUploads() {

        if (storageReferenceOnUploads == null) {
            storageReferenceOnUploads = getFirebaseStorage().getReference("Uploads");
        }

        return storageReferenceOnUploads;
    }
    public static StorageReference getStorageReferenceOnPhotos() {

        if (storageReferenceOnPhotos == null) {
            storageReferenceOnPhotos = getFirebaseStorage().getReference("photos");
        }

        return storageReferenceOnPhotos;
    }
    public static FirebaseUser getCurrentUser() {

        if (currentUser == null){
            currentUser = getAuth().getInstance().getCurrentUser();
        }
        return currentUser;
    }

    public static void initANewBranchWithChild(String branchName, String child, HashMap<String,Object> hashMap){
        getGetFirebaseDatabase().getReference(branchName).child(child).push().setValue(hashMap);

    }
    public static void updateAChild(String branchName, String child, HashMap<String,Object> hashMap){
        getGetFirebaseDatabase().getReference(branchName).child(child).updateChildren(hashMap);
 MyFireBase.getReferenceOnAllUsers().child(MyFireBase.getCurrentUser().getUid())
                .updateChildren(hashMap);

    }

    public static DatabaseReference getReferenceOnAllUsers() {

        return referenceOnAllUsers = getGetFirebaseDatabase().getReference("Users");
    }

    public static DatabaseReference getReferenceOnDeals() {

        return referenceOnAllUsers = getGetFirebaseDatabase().getReference("deals");
    }

    public static DatabaseReference getReferenceOnItems() {

        return referenceOnAllUsers = getGetFirebaseDatabase().getReference("items");
    }

    public static DatabaseReference getReferenceOnColors() {

        return referenceOnAllUsers = getGetFirebaseDatabase().getReference("colors");
    }

    public static DatabaseReference getReferenceOnOrders() {

        return referenceOnAllUsers = getGetFirebaseDatabase().getReference("orders");
    }

    public static DatabaseReference getReferenceOnCurrentUserID() {

        return referenceOnCurrentUser = getGetFirebaseDatabase().getReference("Users").child(getCurrentUser().getUid());
    }

    public static DatabaseReference getReferenceOnChats() {

        return referenceOnChats = getGetFirebaseDatabase().getReference("Chats");
    }

    public static DatabaseReference getReferenceOnChatList() {

        return referenceOnChatList = getGetFirebaseDatabase().getReference("ChatList");
    }

    public static DatabaseReference getReferenceOnPhotos() {

        return referenceOnPhotos = getGetFirebaseDatabase().getReference("Photos");
    }

    public static DatabaseReference getReferenceOnFollowings() {

        return referenceOnFollowings = getGetFirebaseDatabase().getReference("Ahmed");
    }

    public static DatabaseReference getReferenceOnDataBase() {

        return referenceOnDataBase= getGetFirebaseDatabase().getReference();

    }

    public static String getCurrentUserID(){
        return getCurrentUser().getUid();
    }


}
