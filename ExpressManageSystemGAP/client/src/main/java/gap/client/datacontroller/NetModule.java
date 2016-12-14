package gap.client.datacontroller;

import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.gapcomponents.GAPConnectDialog;
import gap.client.util.LocalInfo;
import gap.client.util.MessageType;
import gap.common.dataservice.Contactor;
import gap.common.dataservice.ServiceName;
import gap.common.dataservice.accountdataservice.AccountDataService;
import gap.common.dataservice.expressorderdataservice.ExpressOrderDataService;
import gap.common.dataservice.initialdata.InitialDataService;
import gap.common.dataservice.inventorydataservice.InventoryDataService;
import gap.common.dataservice.logdataservice.LogDataService;
import gap.common.dataservice.managedataservice.InstitutionDataService;
import gap.common.dataservice.orderdataservice.ArrivedOrderDataService;
import gap.common.dataservice.orderdataservice.DeliveryOrderDataService;
import gap.common.dataservice.orderdataservice.LoadOrderDataService;
import gap.common.dataservice.orderdataservice.StockinOrderDataService;
import gap.common.dataservice.orderdataservice.StockoutOrderDataService;
import gap.common.dataservice.receiptdataservice.BillOrderDataService;
import gap.common.dataservice.receiptdataservice.PaymentdataService;
import gap.common.dataservice.strategydataservice.CityDataService;
import gap.common.dataservice.strategydataservice.PriceDataService;
import gap.common.dataservice.strategydataservice.RentDataService;
import gap.common.dataservice.strategydataservice.SalaryDataService;
import gap.common.dataservice.transFareDataService.TransFareDataService;
import gap.common.dataservice.transdataservice.CarDataService;
import gap.common.dataservice.transdataservice.DriverDataService;
import gap.common.dataservice.userdataservice.UserDataService;
import gap.common.netconfig.RMIConfig;
import gap.common.po.UserPO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JFrame;

public class NetModule {

	protected static AccountDataService accountDataService;
	protected static UserDataService userdataservice;
	protected static LogDataService logdataservice;
	protected static CarDataService cardataservice;
	protected static DriverDataService driverdataservice;
	protected static ArrivedOrderDataService arrivedOrderdataservice;
	protected static ExpressOrderDataService expressorderdataservice;
	protected static DeliveryOrderDataService deliveryorderdataservice;
	protected static LoadOrderDataService loadorderdataservice;
	protected static PriceDataService pricedataservice;
	protected static RentDataService rentdataservice;
	protected static SalaryDataService salarydataservice;
	protected static CityDataService citydataservice;
	protected static InstitutionDataService institutiondataservice;
	protected static BillOrderDataService billorderdataservice;
	protected static PaymentdataService paymentdataService;
	protected static InventoryDataService inventorydataservice;
	protected static StockinOrderDataService stockinorderdataservice;
	protected static StockoutOrderDataService stockoutorderdataservice;
	protected static TransFareDataService transFareDataService;
	protected static InitialDataService initialDataService;

	private static Contactor contactor;
	private static boolean dialogShowed = false;

	public static GAPConnectDialog dialog;

	public static boolean isFirstConnect = true;

	// 后台检查连接线程的间隔
	private static long checkIdle = 1000;
	// 断网后尝试连接次数
	private static int checkTimes = 20;

	/**
	 * 根据面板初始化对话框
	 * @param jf
	 */
	public static void initial(JFrame jf) {
		dialogShowed = false;
		dialog = new GAPConnectDialog(jf);
		dialog.setVisible(false);

		// dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		dialog.cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				System.exit(1);
			}
		});

		dialog.confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				dialog.setVisible(false);
			}
		});

		dialog.reconnect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO 自动生成的方法存根
						connect();
					}
				}).start();

			}
		});

	}

	/**
	 * 创建连接的方法
	 */
	public static boolean connect() {
		System.out.println("正在创建连接");
		setConnecting();
		int connect_time = 0;
		boolean reconnect = false;
		while (true) {
			try {
				
				contactor = (Contactor) Naming.lookup(RMIConfig.url
						+ ServiceName.CONTACTOR);

				accountDataService = (AccountDataService) Naming
						.lookup(RMIConfig.url
								+ ServiceName.ACCOUNT_DATA_SERVICE);
				userdataservice = (UserDataService) Naming.lookup(RMIConfig.url
						+ ServiceName.USER_DATA_SERVICE);
				logdataservice = (LogDataService) Naming.lookup(RMIConfig.url
						+ ServiceName.LOG_DATA_SERVICE);
				cardataservice = (CarDataService) Naming.lookup(RMIConfig.url
						+ ServiceName.CAR_DATA_SERVICE);
				driverdataservice = (DriverDataService) Naming
						.lookup(RMIConfig.url + ServiceName.DRIVER_DATA_SERVICE);
				arrivedOrderdataservice = (ArrivedOrderDataService) Naming
						.lookup(RMIConfig.url
								+ ServiceName.ARRIVEDORDER_DATA_SERVICE);
				expressorderdataservice = (ExpressOrderDataService) Naming
						.lookup(RMIConfig.url
								+ ServiceName.EXPRESSORDER_DATA_SERVICE);
				deliveryorderdataservice = (DeliveryOrderDataService) Naming
						.lookup(RMIConfig.url
								+ ServiceName.DELIVERYPORDER_DATA_SERVICE);
				loadorderdataservice = (LoadOrderDataService) Naming
						.lookup(RMIConfig.url
								+ ServiceName.LOADORDER_DATA_SERVICE);
				pricedataservice = (PriceDataService) Naming
						.lookup(RMIConfig.url + ServiceName.PRICE_DATA_SERVICE);
				rentdataservice = (RentDataService) Naming.lookup(RMIConfig.url
						+ ServiceName.RENT_DATA_SERVICE);
				salarydataservice = (SalaryDataService) Naming
						.lookup(RMIConfig.url + ServiceName.SALARY_DATA_SERVICE);
				citydataservice = (CityDataService) Naming.lookup(RMIConfig.url
						+ ServiceName.CITY_DATA_SERVICE);
				institutiondataservice = (InstitutionDataService) Naming
						.lookup(RMIConfig.url
								+ ServiceName.INSTITUTION_DATA_SERVICE);
				billorderdataservice = (BillOrderDataService) Naming
						.lookup(RMIConfig.url
								+ ServiceName.BILLORDER_DATA_SERVICE);
				paymentdataService = (PaymentdataService) Naming
						.lookup(RMIConfig.url
								+ ServiceName.PAYMENT_DATA_SERVICE);
				inventorydataservice = (InventoryDataService) Naming
						.lookup(RMIConfig.url
								+ ServiceName.INVENTORY_DATA_SERVICE);
				stockinorderdataservice = (StockinOrderDataService) Naming
						.lookup(RMIConfig.url
								+ ServiceName.STOCKINORDER_DATA_SERVICE);
				stockoutorderdataservice = (StockoutOrderDataService) Naming
						.lookup(RMIConfig.url
								+ ServiceName.STOCKOUTORDER_DATA_SERVICE);
				transFareDataService = (TransFareDataService) Naming
						.lookup(RMIConfig.url
								+ ServiceName.TRANSFARE_DATA_SERVICE);

				initialDataService = (InitialDataService) Naming
						.lookup(RMIConfig.url
								+ ServiceName.INITIAL_DATA_SERVICE);

				if (isFirstConnect && MainFrame.messagePanel != null) {
					MainFrame.setMessage("连接成功", MessageType.succeed, 2000);
					isFirstConnect = false;
				} else if (MainFrame.messagePanel != null) {
					MainFrame.setMessage("连接成功", MessageType.succeed, 2000);
				}
				setSucceedConnect();
				// 启动检查线程
				Thread chechThread = new Thread(new CheckRunnable());
				chechThread.setDaemon(true);
				chechThread.start();
				System.out.println("连接创建成功！");
				return true;
			} catch (MalformedURLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				showDialog();
				showMessage("连接错误");
				return false;
			} catch (RemoteException | NotBoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				showDialog();
				reconnect = true;
				if (connect_time > checkTimes) {
					showMessage("网络连接错误,请稍后连接");
					setFailConnect();
					return false;
				} else {
					showMessage("网络连接错误,正在尝试重新连接，重连次数：" + (connect_time++)
							+ "次");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
				}
			} 
		}
	}

	private static void showDialog() {
		if (!dialog.isVisible()) {
			dialogShowed = true;
			setConnecting();
			dialog.clear();
			dialog.setVisible(true);
			showMessage("连接中");
		}
	}

	private static void setConnecting() {
		if (dialogShowed) {
			dialog.confirm.setVisible(false);
			dialog.cancel.setVisible(true);
			dialog.reconnect.setVisible(false);
		}
	}

	private static void setFailConnect() {
		if (dialogShowed) {
			dialog.cancel.setVisible(true);
			dialog.reconnect.setVisible(true);
			dialog.cancelConnect.setVisible(false);
		}
	}

	private static void setSucceedConnect() {
		if (dialogShowed) {
			dialog.cancel.setVisible(false);
			dialog.reconnect.setVisible(false);
			dialog.confirm.setVisible(true);
		}
		if (dialog != null) {
			dialog.setVisible(false);
			dialogShowed = false;
		}
	}

	private static void showMessage(String message) {
		if (dialogShowed) {
			dialog.showMessage(message);
			System.out.println("1111");
		} else if (MainFrame.messagePanel != null)
			// System.out.println(message);
			MainFrame.setMessage(message, MessageType.alram, 2000);
	}

	/**
	 * 后台检查线程
	 * 每checkIdel毫秒检查一次网络连接是否正常
	 * @author YangYanfei
	 *
	 */
	static class CheckRunnable implements Runnable {

		@Override
		public void run() {
			// TODO 自动生成的方法存根
			try {
				while (true) {

					if (LocalInfo.localuser != null) {
						UserPO userPO = LocalInfo.localuser.toUserPO();
						String IP = LocalInfo.localIP;
						contactor.getInfo(IP, userPO);
					}
					Thread.sleep(checkIdle);
				}

			} catch (RemoteException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				showMessage("网络连接错误");
				connect();
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}

	}

	// public static void main(String[] args) {
	// // JFrame jf = new JFrame();
	// // jf.setSize(200, 200);
	// // jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// // jf.setLocationRelativeTo(null);
	// // jf.setVisible(true);
	// // initial(jf);.
	// // connect();
	// }
}
