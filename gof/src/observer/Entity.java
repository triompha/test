package observer;

import java.util.Observable;

//����ı��۲�����֪ͨ�۲�����ִ�кβ�������

public class Entity extends Observable{
	public void count(int number){
		for( ; number >=0 ; number-- ){
			setChanged();
			//ע��notifyObservers()��������ʽ��һ�ִ��в�������һ��û�С����ò�������notifyObservers( )����ʱ���ö��󱻴����۲�����update( )������Ϊ��ڶ������������򣬽���update( )��������һ��null������ʹ�õڶ������������ʺ������Ӧ�ó�����κ����͵Ķ��� 
			//Ҳ����˵notifyObservers()�ڲ�ʵ�ʵ��õ���notifyObservers(null);
			notifyObservers(number);
		}
	}

}
