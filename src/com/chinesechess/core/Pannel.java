package com.chinesechess.core;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;
/**
 * 棋盘:将,士,象,马,车,炮,兵
 * */
public class Pannel implements Serializable {
	private static final long serialVersionUID = 1L;
	
 final byte I0=17;//兵/卒
 final byte I1=11;//将/帅
 final byte I2=12;//士/仕
 final byte I3=13;//相/象
 final byte I4=14;//马/码
 final byte I5=15;//车/砗
 final byte I6=16;//炮/泡
 final byte[] F={11,12,12,13,13,14,14,15,15,16,16,17,17,17,17,17,-11,-12,-12,-13,-13,-14,-14,-15,-15,-16,-16,-17,-17,-17,-17,-17};
 final static byte EMPTY=-1;//empty 没有子
 final static byte COVERED=0;//covered 未翻

 final static byte W=8;
 final static byte H=4;

 byte[]table=new byte[W*H];//棋盘，元素是棋子种类id,0表示未翻,-1表示无子; 初始化,采用翻一次随机产生;
 
 byte[]items=new byte[W*H];//棋袋,翻一个=从里面摸一个出来
 byte remain=(byte)items.length;//未翻的棋的个数
 Random rand = new Random();//翻棋时随机数
 //初始化棋盘棋袋
 public void init(){
  Arrays.fill(table, COVERED);
  System.arraycopy(F,0,items,0,F.length);
  remain=(byte)items.length;
 }
 public Pannel() {
	 this.init();
 }
 public byte[] getTable() {
	 return table;
 }
 /**
  * 翻棋
  * @return 翻出来的棋子，0=不是未翻子
  * **/
 public byte discover(byte n){
   if(this.table[n]!=COVERED) return 0;//不是未翻子
   byte p=(byte)rand.nextInt(remain);//产生随机数
   byte v=(byte)items[p];//从剩余棋子中摸一个出来
   System.arraycopy(items,p+1,items,p,remain-p-1);//数组往前移
   remain--;//数量减少1个
   setItem(n,v);//设置当前位置的棋子
   return v;
 }
 /**
  * 走一步:移子,吃子,兑子
  * @param n=from
  * @param m=to
  * @param isBlue:blue 方大于0
  * @return 1=移动,2=兑去;异常情况:3=路线不合法度,4=大小不合法度,5=主动子是空格子或未翻子或是对方的,6=被动子是己方或主动子为炮但被动子空格子或未翻子
  * **/
 public final static byte SUCC_MOV=1;
 public final static byte SUCC_GONE=2;
 public final static byte ERR_ROUTE=3;
 public final static byte ERR_VALUE=4;
 public final static byte ERR_ACTIVE=5;
 public final static byte ERR_PASSIVE=6;
 public byte other(byte n,byte m,boolean isBlue) {
	 byte vn=getItem(n);
	 if(vn==EMPTY)return ERR_ACTIVE;//主动子为空格子
	 if(vn==COVERED)return ERR_ACTIVE;//主动子为未翻子
//	 if((isBlue && vn<0)||(!isBlue && vn>0))return ERR_ACTIVE;//主动子为对方子
	 byte vm=getItem(m);
	 //移子
	 if(vm==EMPTY) {
		 if(!neighborhood(n, m))return ERR_ROUTE;
		 setItem(n,EMPTY);
		 setItem(m,vn);
		 return SUCC_MOV;
	 }
	 //被动子为我方子
//	 if((isBlue && vm>0)||(!isBlue && vm<0))return ERR_PASSIVE;
	 //炮吃子
	 if(Math.abs(vn)==I6) {
		 if(!seperated(n, m))return ERR_ROUTE;
		 setItem(n,EMPTY);
		 setItem(m,vn);
		 return SUCC_MOV;
	 }
	 //被动子为未翻子
	 if(vm==COVERED)return ERR_PASSIVE;
	 //是否可达
	 if(!neighborhood(n, m))return ERR_ROUTE;
	 //兑子
	 if(-vm==vn) {
		 setItem(n,EMPTY);
		 setItem(m,EMPTY);
		 return SUCC_GONE;
	 }
	 //吃子
	 if(Math.abs(vn)==I0) {//主动子是兵卒,
		 if(Math.abs(vm)==I1) {//可被动子将帅
			 setItem(n,EMPTY);
			 setItem(m,vn);
			 return SUCC_MOV;
		 }else {//被动子不是将帅，不能吃
			 return ERR_VALUE;
		 }
	 }
	 if(Math.abs(vn)==I1 && Math.abs(vm)==I0) {//主动子是帅将,被动子是兵卒，不能吃
		 return ERR_VALUE;
	 }
	 //其他情况下
	 if(Math.abs(vn)<Math.abs(vm)) {//主动子可以吃被动子
		 setItem(n,EMPTY);
		 setItem(m,vn);
		 return SUCC_MOV;
	 }else {//主动子无法吃被动子
		 return ERR_VALUE;
	 }
 }
 /**读取位置上的棋子**/
 public byte getItem(byte n){
   return this.table[n];
 }
 /**设置位置上的棋子**/
 public void setItem(byte n,byte v){
   this.table[n]=v;
   System.out.println("setItem:"+Arrays.toString(this.table));
 }
 /**两位置是否相邻**/
 public boolean neighborhood(byte n,byte m){
   byte d=(byte)(n-m);
   if(d==0)return false;//同一个位置
   if(n==0){ if(m==1||m==W)return true;else return false;}   //左上角
   if(n==W-1){ if(m==W-2||d==-W)return true;else return false;}   //右上角
   if(n==H*(W-1)){ if(d==W||d==-1)return true;else return false;}   //左下角
   if(n==H*W-1){ if(d==1||d==W)return true;else return false;}   //右下角
   if(n>0&&n<W-1){ if(d==-W || d==1 || d==-1)return true;else return false; } //上边
   if(n>H*(W-1)&&n<H*W-1){ if(d==W || d==1 || d==-1)return true;else return false; } //下边
   if(n%W==0){ if(d==-1 || d==W || d==-W)return true;else return false; } //左边
   if(n%W==W-1){ if(d==1 || d==W || d==-W)return true;else return false; } //右边
   //四周均空时
   if(d==-1||d==1)return true;//前后相邻
   if(d==-W||d==W)return true;//上下相邻
   return false;
 }
 /**两位置是在一条直线上**/
 private boolean linear(byte n,byte m){
   if(n==m)return false;//不能是同一个位置
   if(neighborhood(n,m))return false;//不能相邻
   if(n/W==m/W)return true;//横线上
   if(n%W==m%W)return true;//纵线上
   return false;//其他情况
 }
 /**两位置中间是否有一子阻隔**/
 public boolean seperated(byte n,byte m){
   if(!linear(n,m))return false;
   byte from=0;byte to=0;
   byte count=0;//阻隔的棋子个数
   if(n<m){ from=n;to=m; }else{ from=m;to=n; }//小的在前大的在后
   if(n/W==m/W){ //位于横线时
     for(int i=from+1;i<to;i++){
      if(table[i]!=EMPTY)count++;//有棋子就累计
     }
     if(count==1)return true;//二者之间只能有一个子
     else return false;
   }
   if(n%W==m%W){ //位于竖线时
     for(int i=from+W;i<to;i+=W){
      if(table[i]!=EMPTY)count++;//有棋子就累计
     }
     if(count==1)return true;//二者之间只能有一个子
     else return false;
   }
   //不会执行到这里的
   return false;
 }
 
}
