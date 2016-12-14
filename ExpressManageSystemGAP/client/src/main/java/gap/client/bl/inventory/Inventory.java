package gap.client.bl.inventory;

import gap.client.blcontroller.InventoryController;
import gap.client.blservice.inventoryblservice.InventoryService;
import gap.client.datacontroller.ControllerFactory;
import gap.client.datacontroller.InventoryDataController;
import gap.client.util.AbstractOperation;
import gap.client.util.ExcelOutput;
import gap.client.util.LocalInfo;
import gap.client.util.Operation;
import gap.client.util.WareHouseSize;
import gap.client.vo.GoodsVO;
import gap.common.po.GoodsPO;
import gap.common.util.ResultMessage;
import gap.common.util.SectorType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Inventory implements InventoryService {
    private static final String ADD = "add", DELETE = "delete",
            MODIFY = "modify";
    InventoryDataController inventoryData;
    List<Operation> operations;

    int shelf = WareHouseSize.SHELF.getSize();
    int unit = WareHouseSize.UNIT.getSize();

    public Inventory() {
        inventoryData = ControllerFactory.getInventoryDataController();
        operations = new ArrayList<Operation>();
    }

    @Override
    public int getTotalNum(String ins_id) {
        int num = 0;
//        List<GoodsPO> list = new ArrayList<GoodsPO>();
//        list = inventoryData.getOneTypeSector(ins_id + "0", ins_id);
//        if (list != null) {
//            num += list.size();
//        }
//
//        list = inventoryData.getOneTypeSector(ins_id + "1", ins_id);
//        if (list != null) {
//            num += list.size();
//        }
//
//        list = inventoryData.getOneTypeSector(ins_id + "2", ins_id);
//        if (list != null) {
//            num += list.size();
//        }
//
//        list = inventoryData.getOneTypeSector(ins_id + "3", ins_id);
//        if (list != null) {
//            num += list.size();
//        }
        num = inventoryData.getTotalNum(ins_id);

        return num;
    }

    @Override
    public List<GoodsVO> getOneSector(String ins_id, String sector_id) {
        // TODO Auto-generated method stub
        List<GoodsPO> list = new ArrayList<GoodsPO>();
        list = inventoryData.getOneSector(sector_id, ins_id);
        return GoodsVO.toVOList(list);
    }

    @Override
    public List<GoodsVO> getOneSectorExisted(String ins_id, String sector_id) {
        // TODO Auto-generated method stub
        List<GoodsPO> list = new ArrayList<GoodsPO>();
        list = inventoryData.getOneSectorExisted(sector_id, ins_id);
        return GoodsVO.toVOList(list);
    }

    public List<GoodsVO> getOneTypeSector(String sector_id, String ins_id) {
        // TODO Auto-generated method stub
        List<GoodsPO> list = new ArrayList<GoodsPO>();
        list = inventoryData.getOneTypeSector(sector_id, ins_id);
        return GoodsVO.toVOList(list);
    }

    @Override
    public double getOneShelfRatio(String position, String sector_id) {
        // TODO Auto-generated method stub
        int num = inventoryData.getOneShelfNum(position, sector_id);
        int units = WareHouseSize.UNIT.getSize();
        return num * 1.0 / units * 100;
    }

    @Override
    public ResultMessage setAlarm(double alarmValue, String ins_id) {
        // TODO Auto-generated method stub
        if (alarmValue < 75 || alarmValue > 100) {
            return ResultMessage.FAILED;
        } else {
            return inventoryData.setAlarm(alarmValue, ins_id);
        }

    }

    @Override
    public double getAlarm(String ins_id) {
        // TODO Auto-generated method stub
        return inventoryData.getAlarm(ins_id);
    }

    @Override
    public ResultMessage distributeSector(String beginColumn, String endColumn,
            String toSector) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void initialadd(GoodsVO vo) {
        // TODO Auto-generated method stub
        System.out.println("add:add");
        operations.add(new AddOperation(vo.toPO()));
    }

    @Override
    public void initialdelete(String id) {
        // TODO Auto-generated method stub
        System.out.println("add:delete");
        operations.add(new DeleteOperation(id));
    }

    @Override
    public void initialmodify(GoodsVO vo) {
        // TODO Auto-generated method stub
        System.out.println("add:modify");
        operations.add(new ModifyOperation(vo.toPO()));
    }

    @Override
    public ResultMessage initialflush() {
        System.out.println(operations.size());
        for (Operation ope : operations) {
            ResultMessage re = ope.excute();
            if (!re.equals(ResultMessage.SUCCEED)) {
                operations.clear();
                return re;
            }
        }
        operations.clear();
        return ResultMessage.SUCCEED;
    }

    @Override
    public ResultMessage stockOut(String id) {
        // TODO Auto-generated method stub
        return inventoryData.delete(id);

    }

    @Override
    public ResultMessage stockIn(GoodsVO vo) {
        // TODO Auto-generated method stub
        return inventoryData.add(vo.toPO());
    }

    @Override
    public String getNextLocation(String ins_id, String sector_id) {
        // TODO Auto-generated method stub
        int size = WareHouseSize.TOTAL.getSize();
        boolean isFull = false;
        boolean[] isUsed = new boolean[size];

        List<GoodsPO> goods = inventoryData.getOneSector(sector_id, ins_id);
        if (goods != null && goods.size() >= size) {
            isFull = true;
            goods = inventoryData.getOneSector(ins_id + "0", ins_id);
        }
        if (goods != null) {
            for (GoodsPO po : goods) {
                int i = locationToInt(po.getLocation());
                isUsed[i - 1] = true;
            }
            int i;
            for (i = 0; isUsed[i] == true; i++)
                ;

            String temp = getLocation(i + 1);
            if (isFull) {
                return "f" + temp;
            } else {
                return temp;
            }

        } else {

            String temp = getLocation(1);
            if (isFull) {
                return "f" + temp;
            } else {
                return temp;
            }
        }

    }

    public int locationToInt(String location) {
        String[] detail = location.split(",");
        int num = 0;
        if (detail.length == 3) {
            num += (detail[0].charAt(0) - 'A') * shelf * unit;
            num += (detail[1].charAt(0) - 'A') * unit;
            num += Integer.parseInt(detail[2]);
            return num;
        }
        return -1;
    }

    public String getLocation(int num) {
        if (num > 0 && num <= WareHouseSize.TOTAL.getSize()) {
            int[] size = new int[3];
            size[0] = num / (unit * shelf);
            // System.out.println(size[0]);
            num -= size[0] * unit * shelf;
            // System.out.println(num);
            size[1] = num / unit;
            num -= size[1] * unit;
            if (num == 0) {
                size[1]--;
                size[2] = unit;
            } else {
                size[2] = num;
            }
            String temp = size[2] + "";

            String l = (char) (size[0] + 'A') + "," + (char) (size[1] + 'A')
                    + "," + temp;

            return l;
        }
        System.out.println("location: wrong number");
        return null;

    }

    @Override
    public String Alarm(String sector_id, String ins_id) {
        // TODO Auto-generated method stub
        int size = WareHouseSize.TOTAL.getSize();
        double alarm = getAlarm(ins_id);
        double used = 0;
        List<GoodsPO> pos = inventoryData.getOneTypeSector(sector_id, ins_id);
        if (pos != null && pos.size() > 0) {
            used = pos.size();
            used = 100 * used / size;
        }

        if (used > alarm) {
            return SectorType.getName(sector_id.charAt(7)) + " " + used;
        } else {
            return null;
        }

    }

    public ResultMessage exportExcel(String path) {
        // TODO Auto-generated method stub
        String loc = "排架位", sec = "分区编号", order_id = "快递编号", intime = "入库时间";
        String belong_sec = "所属分区编号", destin = "目的地";
        String ins_id = LocalInfo.ins_id;
        System.out.println(ins_id);
        List<GoodsVO> list0 = new ArrayList<GoodsVO>();
        List<GoodsVO> list1 = new ArrayList<GoodsVO>();
        List<GoodsVO> list2 = new ArrayList<GoodsVO>();
        List<GoodsVO> list3 = new ArrayList<GoodsVO>();

        list0 = InventoryController.getOneTypeSector(ins_id + "0", ins_id);

        list1 = InventoryController.getOneTypeSector(ins_id + "1", ins_id);
        list2 = InventoryController.getOneTypeSector(ins_id + "2", ins_id);
        list3 = InventoryController.getOneTypeSector(ins_id + "3", ins_id);

        List<GoodsVO> list = new ArrayList<GoodsVO>();
        list.addAll(list0);
        list.addAll(list1);
        list.addAll(list2);
        list.addAll(list3);

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
        String version = df.format(new Date());// new Date()为获取当前系统时间
        String fileName = "库存盘点" + version + ".xls";

        ExcelOutput output = new ExcelOutput(order_id, sec, loc, intime,
                belong_sec, destin);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                GoodsVO vo = list.get(i);
                System.out.println(vo.getExpressorder_id());
                output.appendRow(vo.getExpressorder_id(), vo.getSector_id(),
                        vo.getLocation(), vo.getDate(),
                        vo.getBelong_sector_id(), vo.getDestination());
            }

        }

        output.export(path, fileName);

        return null;
    }

    public ExcelOutput addList(ExcelOutput output, List<GoodsVO> list) {
        try {
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    GoodsVO vo = list.get(i);
                    output.appendRow(vo.getExpressorder_id(),
                            vo.getSector_id(), vo.getLocation(), vo.getDate(),
                            vo.getBelong_sector_id(), vo.getDestination());

                }

            }
            return output;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    class AddOperation extends AbstractOperation {
        public AddOperation(Object args) {
            super(inventoryData, ADD, args);
        }
    }

    class DeleteOperation extends AbstractOperation {
        public DeleteOperation(Object args) {
            super(inventoryData, DELETE, args);
        }
    }

    class ModifyOperation extends AbstractOperation {
        public ModifyOperation(Object args) {
            super(inventoryData, MODIFY, args);
        }
    }

}
