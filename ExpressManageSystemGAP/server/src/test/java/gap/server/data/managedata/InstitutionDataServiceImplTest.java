package gap.server.data.managedata;

import gap.common.dataservice.ServiceName;
import gap.common.dataservice.managedataservice.InstitutionDataService;
import gap.common.netconfig.RMIConfig;
import gap.common.po.InstitutionPO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

public class InstitutionDataServiceImplTest {

	@Before
	public void setup() throws Exception {
		// NetInitial.initial();
	}

	@Test
	public void test() throws RemoteException, MalformedURLException,
			NotBoundException {
		InstitutionDataService institutiondata = (InstitutionDataService) Naming
				.lookup(RMIConfig.url + ServiceName.INSTITUTION_DATA_SERVICE);
		// InstitutionPO po1 = new InstitutionPO("0010002", "南京市栖霞区营业厅", "南京市",
		// 53);
		InstitutionPO po2 = new InstitutionPO("0011001", "南京市栖霞区中转中心", "南京市",
				28);
		InstitutionPO po3 = new InstitutionPO("0020001", "北京市栖霞区营业厅", "北京市", 53);
		InstitutionPO po4 = new InstitutionPO("0021001", "北京市栖霞区中转中心", "北京市",
				28);
		InstitutionPO po5 = new InstitutionPO("0030001", "广州市栖霞区营业厅", "广州市", 53);
		InstitutionPO po6 = new InstitutionPO("0031001", "广州市栖霞区中转中心", "广州市",
				28);
		InstitutionPO po7 = new InstitutionPO("0040001", "上海市静安区营业厅", "上海市", 45);
		InstitutionPO po8 = new InstitutionPO("0041001", "上海市静安区中转中心", "上海市",
				45);
		// System.out.println(institutiondata.add(po1).getMessage());
		System.out.println(institutiondata.add(po2).getMessage());
		System.out.println(institutiondata.add(po3).getMessage());
		System.out.println(institutiondata.add(po4).getMessage());
		System.out.println(institutiondata.add(po5).getMessage());
		System.out.println(institutiondata.add(po6).getMessage());
		System.out.println(institutiondata.add(po7).getMessage());
		System.out.println(institutiondata.add(po8).getMessage());
		InstitutionPO ins = institutiondata.findById("0010002");
		System.out.println(ins.getInsId() + ins.getInsCity() + ins.getInsName()
				+ ins.getInsMember() + ins.getInsType());
		InstitutionPO po9 = new InstitutionPO("0011001", "南京市玄武区中转中心", "南京市",
				65);
		System.out.println(institutiondata.modify(po9).getMessage());
		System.out.println("GET BY CITY");
		for (InstitutionPO get : institutiondata.findByCity("南京市")) {
			System.out.println(get.getInsId() + get.getInsCity()
					+ get.getInsName() + get.getInsMember() + get.getInsType());
		}

		System.out.println("GET ALL");
		for (InstitutionPO get : institutiondata.getAll()) {
			System.out.println(get.getInsId() + get.getInsCity()
					+ get.getInsName() + get.getInsMember() + get.getInsType());
		}

	}

}
