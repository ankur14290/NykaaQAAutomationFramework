package FrameWorkNykaa;
//package FrameWorkNykaa;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.LinkedBlockingQueue;
//
//import DataNykaa.AccountData;
//
///**
// * @author nevil
// */
//public class LoginSync {
//
//    public volatile static LoginSync INSTANCE = null;
//    public volatile static BlockingQueue<AccountData> UsedAccountLogine = new LinkedBlockingQueue<AccountData>();
//    public volatile static BlockingQueue<AccountData> FreeListLoginQue = new LinkedBlockingQueue<AccountData>();
//    HashMap<String, String> hm = new HashMap<String, String>();
//
//    private LoginSync() {
//    }
//
//    public static void init() {
//
//    }
//
//    public static synchronized LoginSync getInstance() {
//        if (INSTANCE == null) {
//            INSTANCE = new LoginSync();
//            INSTANCE.populateLogins();
//        }
//        return INSTANCE;
//    }
//
//    /*
//     * protected Object clone() throws CloneNotSupportedException { throw new
//     * CloneNotSupportedException(); }
//     */
//    private void populateLogins() {
//
//        Framework framework = new Framework();
//        List<AccountData> accountDataList = framework.getDataList(AccountData.class, "loginset");
//        FreeListLoginQue = new LinkedBlockingQueue<AccountData>();
//        for (AccountData accountData : accountDataList) {
//            FreeListLoginQue.add(accountData);
//
//        }
//
//        // Set<Entry<String, String>> entries = hm.entrySet();
//        // FreeLoginQue = new
//        // LinkedBlockingQueue<HashMap.Entry<String,String>>(entries);
//
//    }
//    // hm.
//    // hm.put("nevil.panchal","Password123");
//
//    public AccountData getLogin() throws InterruptedException {
//        // populateLogins();// not required
//        // Entry<String, String> login = FreeLoginQue.take();
//        AccountData accountData = FreeListLoginQue.take();
//        UsedAccountLogine.put(accountData);
//        // UsedLoginque.put(login);
//
//        return accountData;
//    }
//
//    public void clearLoginLock(AccountData accountData) throws InterruptedException {
//        if (UsedAccountLogine.remove(accountData)) {
//            FreeListLoginQue.put(accountData);
//        }
//
//    }
//
//}
