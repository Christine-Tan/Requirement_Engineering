package gap.client.ui.UITools;

import java.awt.Color;

/**
 * 
 * 用来渐变颜色的类，传入起始颜色、目标颜色和改变幅度（0至1）<br/>
 * 通过isFinish判断是否改变完毕，调用change获得改变一次后的颜色<br/>
 * @author 申彬
 *
 */
public class ColorChanger {

	
	Color formerColor;
	Color targetColor;	

	
	//r，g，b的差距
	double[] formerRGB = new double[3];
	double[] targetRGB = new double[3];
	double[] diff = new double[3];
	double[] currentRGB = new double[3];
	
	
	double rate;
	
	boolean[] isFinish;
	
	public ColorChanger(Color formerColor,Color targetColor,double rate){
		this.formerColor = formerColor;
		this.targetColor = targetColor;
		this.rate = rate;
		
		formerRGB[0] = formerColor.getRed();
		formerRGB[1] = formerColor.getGreen();
		formerRGB[2] = formerColor.getBlue();
		
		//起始状态
		for(int i=0;i<formerRGB.length;i++){
			currentRGB[i] = formerRGB[i];
		}
		
		targetRGB[0] = targetColor.getRed();
		targetRGB[1] = targetColor.getGreen();
		targetRGB[2] = targetColor.getBlue();
		
		//差距
		for(int i=0;i<diff.length;i++){
			diff[i] = targetRGB[i] - formerRGB[i];
		}
		
		isFinish = new boolean[3];
		for(int i=0;i<isFinish.length;i++){
			isFinish[i] = false;
		}
	}
	
	
	public boolean isFinish(){
		boolean result = true;
		for(boolean isOneFinish:isFinish){
			result = result && isOneFinish;
		}
		return result;
	}
	
	public Color change(){
		
		for(int i=0;i<currentRGB.length;i++){
			
			if(Math.abs(targetRGB[i] - currentRGB[i]) <=Math.abs(diff[i]*rate)){
				currentRGB[i] = targetRGB[i];
				isFinish[i] = true;
			}else{
				currentRGB[i] += diff[i]*rate;
			}
			
		}	
		
		int[] realColor = new int[3];
		realColor[0] = (int) currentRGB[0];
		realColor[1] = (int) currentRGB[1];
		realColor[2] = (int) currentRGB[2];
		
		return new Color(realColor[0],realColor[1],realColor[2]);
	}
}
