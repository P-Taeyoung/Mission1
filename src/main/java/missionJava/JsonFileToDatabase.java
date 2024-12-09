package missionJava;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;


public class JsonFileToDatabase {

    public static String toDate(String dateString) {
        long datetime = Long.parseLong(dateString);
        Date date = new Date(datetime);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }

    public static void toDatabase() throws FileNotFoundException {
        Reader reader = new FileReader("/Users/taeyeong/Desktop/zerobase/zerobaseMission1/src/main/java/missionJava/seoul_wifi_info.json");
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
        JsonArray jsonArray = jsonObject.get("DATA").getAsJsonArray();

        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject obj = (JsonObject) jsonArray.get(i);
            String mgrNo = obj.get("x_swifi_mgr_no").getAsString(); // 관리번호
            String wrdofc = obj.get("x_swifi_wrdofc").getAsString(); // 자치구
            String mainNm = obj.get("x_swifi_main_nm").getAsString(); // 와이파이명
            String adres1 = obj.get("x_swifi_adres1").getAsString(); // 도로명 주소
            String adres2 = obj.get("x_swifi_adres2").getAsString(); // 상세주소
            String instlFloor = obj.get("x_swifi_instl_floor").getAsString().replaceAll("\\s", ""); // 설치위치
            String instlTy = obj.get("x_swifi_instl_ty").getAsString(); // 설치유형
            String instlMby = obj.get("x_swifi_instl_mby").getAsString(); //설치기관
            String svcSe = obj.get("x_swifi_svc_se").getAsString(); // 서비스구분
            String cmcwr = obj.get("x_swifi_cmcwr").getAsString(); // 망종류
            String cnstcYear = obj.get("x_swifi_cnstc_year").getAsString(); // 설치년도
            String inoutDoor = obj.get("x_swifi_inout_door").getAsString(); // 실내외구분
            String remars3 = obj.get("x_swifi_remars3").getAsString().replaceAll("\\s", ""); // WIFI접속환경
            Double lnt = obj.get("lnt").getAsDouble(); // X좌표
            Double lat = obj.get("lat").getAsDouble(); // Y좌표
            String dttm = toDate(obj.get("work_dttm").getAsString()); // 작업일자
            WifiInfoData wifiInfoData = new WifiInfoData();
            wifiInfoData.setMgrNo(mgrNo);
            wifiInfoData.setWrdofc(wrdofc);
            wifiInfoData.setMainNm(mainNm);
            wifiInfoData.setAdres1(adres1);
            wifiInfoData.setAdres2(adres2);
            wifiInfoData.setInstlFloor(instlFloor);
            wifiInfoData.setInstlTy(instlTy);
            wifiInfoData.setInstlMby(instlMby);
            wifiInfoData.setSvcSe(svcSe);
            wifiInfoData.setCmcwr(cmcwr);
            wifiInfoData.setCnstcYear(cnstcYear);
            wifiInfoData.setInoutDoor(inoutDoor);
            wifiInfoData.setRemars3(remars3);
            wifiInfoData.setLnt(lnt);
            wifiInfoData.setLat(lat);
            wifiInfoData.setDttm(dttm);

            WifiService wifiService = new WifiService();
            wifiService.setWifiInfo(wifiInfoData);
        }
    }
}
