# Hello, welcome to Homesul
If you hit the proximity sensor cup, you may feel like drinking alcohol together.
You can use messages with correct drinking and bulletin boards.

## Process
* '잔디' 협업툴 사용
* 팀명세서: https://drive.google.com/open?id=1MbZrc6EK6toNGroFogNIOXCvSpWHfWzx  
* 기획명세서: https://drive.google.com/open?id=1dD6bSSzqrXgiQPbnIT-1Q0Pf8HPUdaQ-  
* 설계명세서: https://drive.google.com/open?id=19iw3jhk072sPvHwu24jYw3MD8hwQVTrw  
* 액티비티스토리보드: https://drive.google.com/open?id=1E3O4OeFsjTiopqV2ogeipmvk2_KM49i0  
  
  
1주차 정리: https://drive.google.com/open?id=19FcyaYanK4AgCxqYDDAq7L4jh8rwXgRc  
2주차 정리: https://drive.google.com/open?id=1FlVupr4HBMCh4adYdRibCixR2bIRC7_A  
3주차 정리: https://drive.google.com/open?id=118fMa-zQtJOA12u62xi1pRtQk4GqUExm  
4주차 정리: https://drive.google.com/open?id=1ITUTAdKfmPIvvHsDNQ8E58KMfnDHp_nj  
5주차 정리: https://drive.google.com/open?id=1iA_eCdSrTPL2hD3qt0Ansu4JjkkVH0BX  
6주차 정리: https://drive.google.com/open?id=1Cv_DDqqYX6ttm0AqzDDpZNwHWdGo8zy0  
7주차 정리: https://drive.google.com/open?id=15dEnovOsVDDwvqBILFwZ3OJoXSB-tT7R  
8주차 정리: https://drive.google.com/open?id=1C7ELoR0GTjUZyQAx4cxhH-NnoL989SiS  



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
