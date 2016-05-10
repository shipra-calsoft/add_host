import com.vmware.vim25.*;
import com.vmware.vim25.mo.util.*;
import com.vmware.vim25.ws.WSClient;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream.GetField;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Properties;
import com.vmware.vim25.mo.ClusterComputeResource;
import com.vmware.vim25.mo.Datacenter;
import com.vmware.vim25.mo.Datastore;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.HostSystem;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.ResourcePool;
import com.vmware.vim25.mo.ServiceInstance;
public class add_host{
public static void main(String args[]) throws RemoteException, MalformedURLException
{
	Properties prop = new Properties();
	try{
	prop.load(Service_Inst.class.getClassLoader().getResourceAsStream("config.properties"));	
	   }catch(Exception e){}
	String s = prop.getProperty("url");
	ServiceInstance si = new ServiceInstance(new URL(prop.getProperty("url")), prop.getProperty("username"), prop.getProperty("password"), true);
	Folder rootFolder = si.getRootFolder();
	ClusterConfigSpec spec1 = new ClusterConfigSpec();
	HostConnectSpec spec = new HostConnectSpec();
	spec.setHostName(prop.getProperty("ip"));
	spec.setSslThumbprint(prop.getProperty("thumbprint"));
	spec.setPassword(prop.getProperty("pass"));
	spec.setPort(443);
	spec.setUserName(prop.getProperty("uname"));
	ResourcePool resourcePool = null;
	Datacenter dc = rootFolder.createDatacenter(prop.getProperty("datacentr"));
	Folder fd = dc.getHostFolder();
	ClusterComputeResource cd = fd.createCluster(prop.getProperty("cluster"), spec1);
	cd.addHost_Task(spec, true, resourcePool);
}
}
public class Service_Inst {

}
