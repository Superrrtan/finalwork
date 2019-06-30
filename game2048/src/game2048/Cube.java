package game2048;
import java.awt.Font;
import java.awt.Color;
import javax.swing.*;

class Cube {
	
	public final static Color Yellow01 = new Color(245,222,179);
	public final static Color Yellow02 = new Color(227,207,87);
	public final static Color Yellow03 = new Color(255,227,132);
	public final static Color Yellow04 = new Color(255,125,0);
	public final static Color Yellow05 = new Color(237,145,33);
	public final static Color Yellow06 = new Color(255,153,18);
	public final static Color Yellow07 = new Color(255,128,0);
	public final static Color Yellow08 = new Color(255,97,0);
	public final static Color Yellow09 = new Color(227,168,105);
	public final static Color Yellow010 = new Color(210,168,105);
	public final static Color Yellow011 = new Color(255,125,64);
	
	
    private int num;
    private JButton btn;
    private boolean isAdded;

    /**
     * ����һ������
     */
    public Cube() {
        btn = new JButton();
        btn.setFont(new Font("΢���ź�", Font.BOLD, 24));
        btn.setEnabled(false);
        num = 0;
        isAdded = false;
    }

    /**
     * �����ı�����
     * 
     * @param n
     *            ��ֵ
     */
    public void setText(int n) {
        num = n; 
        btn.setText(n != 0 ? n + "" : "");
        }
    
    public void setColor(){
    	String number = String.valueOf(num);
    	btn.setBorderPainted(false);
    	btn.setOpaque(true);
    	switch(number){
		case "2":
			btn.setBackground(Yellow01);
			break;
		case "4":
			btn.setBackground(Yellow02);
			break;
		case "8":
			btn.setBackground(Yellow03);
			break;
		case "16":
			btn.setBackground(Yellow04);
			break;
		case "32":
			btn.setBackground(Yellow05);
			break;
		case "64":
			btn.setBackground(Yellow06);
			break;
		case "128":
			btn.setBackground(Yellow07);
			break;
		case "256":
			btn.setBackground(Yellow08);
			break;
		case "512":
			btn.setBackground(Yellow09);
			break;
		case "1024":
			btn.setBackground(Yellow010);
			break;
		case "2048":
			btn.setBackground(Yellow011);
			break;    
		default:
			btn.setBorderPainted(true);
	    	btn.setOpaque(false);
			break;
		}		
    	
	}
    

    /**
     * ��ȡ�ؼ�
     * 
     * @return JButton
     */
    public JButton getView() {
        return btn;
    }

    /**
     * ��ȡ��ֵ
     * 
     * @return int
     */
    public int getNum() {
        return num;
    }

    /**
     * �Ƿ�����Ӷ��� �޵�ǰ�ƶ�����Ч,�ƶ�������Ļ�Ĭ��ֵ-false
     * 
     * @return
     */
    public boolean isAdded() {
        return isAdded;
    }

    /**
     * �޸����ɷ�ʽ
     * 
     * @param b
     *            true-��Ӷ���
     */
    public void setIsAdded(boolean b) {
        isAdded = b;
    }
}
