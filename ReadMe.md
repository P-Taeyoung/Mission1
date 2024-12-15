1. 공공 와이파이 API를 활용하여 mariadb를 통해 데이터베이스에 WIFI 정보들을 저장함
2. form을 이용하여 데이터를 서버에 전송
3. WifiService 클래스를 통해 자바파일로 데이터베이스에 접근하는 기능 생성
4. JsonFileToDatabase와 WifiSerive.setWifiInfo 를 통해 와이파이정보가 담긴 json파일을 모두 데이터베이스에 삽입 (insert)
5. WifiService.setMyLocation을 통해 내 위치 정보를 history_search 테이블에 삽입(insert)
6. WifiService.historyMyLocation을 통해 history_search 테이블을 불러옴(select)
7. WifiService.nearWifiInfo을 통해 근처 와이파이정보를 불러옴(select)
8. WifiService.removeHistory를 통해 history_search에 있는 데이터 삭제(delete)
9. 안풀리는 부분 -> 내 위치 가져오기 버튼을 누른 후에 직접 위도 경도 정보를 입력하고 근처 와이파이 정보를 가져오기 버튼을 클릭하면 다시 내 위치 위도 경도정보가 가져와지면서 내가 직접 입력한 정보들이 없어짐. 반대로  내 위치를 가져오기 전에 직접 정보 입력 후 와이파이 정보를 가져온 후에 내 위치 가져오기 버튼을 가져오면 의도대로 먼저 직접 입력한 위치 정보를 불러온 후에 내 위치 정보를 가져옴. 왜 이런지 모르겠음. 