# RobotiumTestRunner
  
自动化测试运行工具    
   Java写的自动化运行工具 ，目前仅自己使用，大家如果想使用，还需要再等过一段时间我整理一下；   

基本原理：  
        利用java运行命令行来执行adb指令，完成想要的操作  
目前具备的功能：  
1.界面化的用例运行管理   支持多机切换运行
2.读取机器的品牌，型号，API版本，android系统版本信息  
3.导入外部数据驱动到手机sdcard根目录  
4.读取电脑xml文件中的用例，选择以及运行用例  
5.导出结果xml到电脑  
6.提供系统控制台，来输出实时运行情况  

后续完善的功能：  （工具类已经写好）  
1.发送结果到邮件    
2.定时执行    
3.统一文件路径问题 1)数据驱动 以及 config文件路径 2)导出的xml结果文件的路径 3)xsl模版路径 以及 转化的html结果路径  

因为最近工作比较紧，等到可以直接下载使用的时候，会在此说明；