# Hello, welcome to BeaconLuv
Various events can be output through the beacon.  
The serial number of each beacon is registered, and the event according to each beacon is as follows.  
beacon1. Coupon page  
beacon2. Board Page  
The beacon operates as Bluetooth after performing a transmission range (SSID) check.  

## Process
<img width="800" height="400" alt="" src="https://user-images.githubusercontent.com/19817832/60803404-1631e800-a1b6-11e9-8dba-7a6b9866b404.PNG"/>


# H/W Model
<img width="600" height="400" alt="" src="https://user-images.githubusercontent.com/19817832/60803214-b0456080-a1b5-11e9-816c-77eb4df0cbfc.png"/>


## Demo  
<img width="600" height="500" alt="" src="https://user-images.githubusercontent.com/19817832/60799429-1928da80-a1ae-11e9-9b26-eb9331f98ee4.gif"/>

<h2>How to use?</h2>
  
  
<img width="600" height="500" alt="" src="https://user-images.githubusercontent.com/19817832/60801866-bab22b00-a1b2-11e9-9452-e36ffbc605d8.gif"/>
<ul>
<li>beacon1 connection -> Coupon Page</li>
</ul>
  
  
<img width="600" height="500" alt="" src="https://user-images.githubusercontent.com/19817832/60802230-6c515c00-a1b3-11e9-85a0-4094bdc4c43b.gif"/>
<ul>
<li>beacon2 connection -> Board Page</li>
</ul>
  
  
<img width="600" height="500" alt="" src="https://user-images.githubusercontent.com/19817832/60802480-ebdf2b00-a1b3-11e9-85da-7343f3281cd4.gif"/>
<ul>
<li>beacon2 connection -> Board Page -> insert </li>
</ul>

# Setup

// Android 디바이스가 Beacon의 송신 범위에 들어가거나 나왔을 때 체크한다.
        //OnEnterRegion : 등록 된 지역에서 비콘에 처음 도달 가능하게 될 때 발생합니다.
        beaconManager.setMonitoringListener(new BeaconManager.MonitoringListener() {
            @Override
            public void onEnteredRegion(Region region, List<Beacon> list) {  //지역(모니터링 된 지역) , List <Beacon> 비콘 (이벤트를 트리거 한 비콘 목록)
                showNotification("Cheerluv Team 첫 번째 비콘", "게시판 비콘 연결되었습니다." + "연결됬어! " + list.get(0).getRssi());      
         //OnExitRegion : 지역에 등록 된 모든 신호가 도달 불능이되었을 때 발생합니다.
            @Override
            public void onExitedRegion(Region region) {
               cancelNotification(); //해제할때 필요
               /*howNotification("Cheerluv Team", "비콘 해제되었습니다." );*/
            }
        });
