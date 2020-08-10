package com.chinesechess.core;

import java.util.Arrays;
import java.util.Random;

public class Pannel{//����:��,ʿ,��,��,��,��,��
 final byte I0=17;//��/��
 final byte I1=11;//��/˧
 final byte I2=12;//ʿ/��
 final byte I3=13;//��/��
 final byte I4=14;//��/��
 final byte I5=15;//��/�
 final byte I6=16;//��/��
 final byte[] F={11,12,12,13,13,14,14,15,15,16,16,17,17,17,17,17,-11,-12,-12,-13,-13,-14,-14,-15,-15,-16,-16,-17,-17,-17,-17,-17};
 final static byte EMPTY=-1;//empty û����
 final static byte COVERED=0;//covered δ��

 final static byte W=8;
 final static byte H=4;

 byte[]table=new byte[W*H];//���̣�Ԫ������������id,0��ʾδ��,-1��ʾ����; ��ʼ��,���÷�һ���������;
 
 byte[]items=new byte[W*H];//���,��һ��=��������һ������
 byte remain=(byte)items.length;//δ������ĸ���
 Random rand = new Random();//����ʱ�����
 //��ʼ���������
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
  * ����
  * @return �����������ӣ�0=����δ����
  * **/
 public byte discover(byte n){
   if(this.table[n]!=COVERED) return 0;//����δ����
   byte p=(byte)rand.nextInt(remain);//���������
   byte v=(byte)items[p];//��ʣ����������һ������
   System.arraycopy(items,p+1,items,p,remain-p-1);//������ǰ��
   remain--;//��������1��
   setItem(n,v);//���õ�ǰλ�õ�����
   return v;
 }
 /**
  * ��һ��:����,����,����
  * @param n=from
  * @param m=to
  * @param isBlue:blue ������0
  * @return 1=�ƶ�,2=��ȥ;�쳣���:3=·�߲��Ϸ���,4=��С���Ϸ���,5=�������ǿո��ӻ�δ���ӻ��ǶԷ���,6=�������Ǽ�����������Ϊ�ڵ������ӿո��ӻ�δ����
  * **/
 public final static byte SUCC_MOV=1;
 public final static byte SUCC_GONE=2;
 public final static byte ERR_ROUTE=3;
 public final static byte ERR_VALUE=4;
 public final static byte ERR_ACTIVE=5;
 public final static byte ERR_PASSIVE=6;
 public byte other(byte n,byte m,boolean isBlue) {
	 byte vn=getItem(n);
	 if(vn==EMPTY)return ERR_ACTIVE;//������Ϊ�ո���
	 if(vn==COVERED)return ERR_ACTIVE;//������Ϊδ����
//	 if((isBlue && vn<0)||(!isBlue && vn>0))return ERR_ACTIVE;//������Ϊ�Է���
	 byte vm=getItem(m);
	 //����
	 if(vm==EMPTY) {
		 if(!neighborhood(n, m))return ERR_ROUTE;
		 setItem(n,EMPTY);
		 setItem(m,vn);
		 return SUCC_MOV;
	 }
	 //������Ϊ�ҷ���
//	 if((isBlue && vm>0)||(!isBlue && vm<0))return ERR_PASSIVE;
	 //�ڳ���
	 if(Math.abs(vn)==I6) {
		 if(!seperated(n, m))return ERR_ROUTE;
		 setItem(n,EMPTY);
		 setItem(m,vn);
		 return SUCC_MOV;
	 }
	 //������Ϊδ����
	 if(vm==COVERED)return ERR_PASSIVE;
	 //�Ƿ�ɴ�
	 if(!neighborhood(n, m))return ERR_ROUTE;
	 //����
	 if(-vm==vn) {
		 setItem(n,EMPTY);
		 setItem(m,EMPTY);
		 return SUCC_GONE;
	 }
	 //����
	 if(Math.abs(vn)==I0) {//�������Ǳ���,
		 if(Math.abs(vm)==I1) {//�ɱ����ӽ�˧
			 setItem(n,EMPTY);
			 setItem(m,vn);
			 return SUCC_MOV;
		 }else {//�����Ӳ��ǽ�˧�����ܳ�
			 return ERR_VALUE;
		 }
	 }
	 if(Math.abs(vn)==I1 && Math.abs(vm)==I0) {//��������˧��,�������Ǳ��䣬���ܳ�
		 return ERR_VALUE;
	 }
	 //���������
	 if(Math.abs(vn)<Math.abs(vm)) {//�����ӿ��ԳԱ�����
		 setItem(n,EMPTY);
		 setItem(m,vn);
		 return SUCC_MOV;
	 }else {//�������޷��Ա�����
		 return ERR_VALUE;
	 }
 }
 /**��ȡλ���ϵ�����**/
 public byte getItem(byte n){
   return this.table[n];
 }
 /**����λ���ϵ�����**/
 public void setItem(byte n,byte v){
   this.table[n]=v;
   System.out.println("setItem:"+Arrays.toString(this.table));
 }
 /**��λ���Ƿ�����**/
 public boolean neighborhood(byte n,byte m){
   byte d=(byte)(n-m);
   if(d==0)return false;//ͬһ��λ��
   if(n==0){ if(m==1||m==W)return true;else return false;}   //���Ͻ�
   if(n==W-1){ if(m==W-2||d==-W)return true;else return false;}   //���Ͻ�
   if(n==H*(W-1)){ if(d==W||d==-1)return true;else return false;}   //���½�
   if(n==H*W-1){ if(d==1||d==W)return true;else return false;}   //���½�
   if(n>0&&n<W-1){ if(d==-W || d==1 || d==-1)return true;else return false; } //�ϱ�
   if(n>H*(W-1)&&n<H*W-1){ if(d==W || d==1 || d==-1)return true;else return false; } //�±�
   if(n%W==0){ if(d==-1 || d==W || d==-W)return true;else return false; } //���
   if(n%W==W-1){ if(d==1 || d==W || d==-W)return true;else return false; } //�ұ�
   //���ܾ���ʱ
   if(d==-1||d==1)return true;//ǰ������
   if(d==-W||d==W)return true;//��������
   return false;
 }
 /**��λ������һ��ֱ����**/
 private boolean linear(byte n,byte m){
   if(n==m)return false;//������ͬһ��λ��
   if(neighborhood(n,m))return false;//��������
   if(n/W==m/W)return true;//������
   if(n%W==m%W)return true;//������
   return false;//�������
 }
 /**��λ���м��Ƿ���һ�����**/
 public boolean seperated(byte n,byte m){
   if(!linear(n,m))return false;
   byte from=0;byte to=0;
   byte count=0;//��������Ӹ���
   if(n<m){ from=n;to=m; }else{ from=m;to=n; }//С����ǰ����ں�
   if(n/W==m/W){ //λ�ں���ʱ
     for(int i=from+1;i<to;i++){
      if(table[i]!=EMPTY)count++;//�����Ӿ��ۼ�
     }
     if(count==1)return true;//����֮��ֻ����һ����
     else return false;
   }
   if(n%W==m%W){ //λ������ʱ
     for(int i=from+W;i<to;i+=W){
      if(table[i]!=EMPTY)count++;//�����Ӿ��ۼ�
     }
     if(count==1)return true;//����֮��ֻ����һ����
     else return false;
   }
   //����ִ�е������
   return false;
 }
 
}
