package game2048;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Game2048 extends JFrame {
    // ��¼�����ƶ������ƶ��ĸ�������ʼ��Ϊʮ������
    final public static int MOVE_UP = 0xf37;
    final public static int MOVE_DOWN = 0xf36;
    final public static int MOVE_LEFT = 0xf35;
    final public static int MOVE_RIGHT = 0xf34;
    // ��Ϸ״̬
    final public static int GAME_OVER = 0xf33;
    final public static int GAME_CONTINUE = 0xf32;
    final public static int GAME_WIN = 0xf31;
    // ��ť�¼�
    final public static int BUTTON_NEW_GAME = 0xf30;
    final public static int BUTTON_ABOUT = 0xf28;
    final public static int BUTTON_EXIT = 0xf27;

    //����
    private int column;
    //����
    private int row;
    //��Ϸ״̬
    private int gameState;
    //����
    private HashMap<Point, Cube>  viewList = new HashMap<>();
    //�Ƿְ�
    private JMenuItem scoreBoard;
    //�Ʋ���
    private JMenuItem arithmometer;
    //����
    private int count;
    //��Ϸ�Ѷ�
    private int gameLv=0;
    private JMenuItem timer;
    long Start;//��¼��ʼʱ��
    long elapsed;//��¼����ʱ
    int task=2048;
    
    PlaySounds play=new PlaySounds("music/press.wav");

    public static void main(String[] args) {
    	MainMenu mm= new MainMenu();//�����˵�ҳ
    	mm.mainFace();
    }

    /**
     * ����һ��ָ����ߵĽ���
     * 
     * @param width
     *            ��
     * @param height
     *            ��
     */
    public Game2048(int width, int height) {
        column = width / 100;
        row = height / 100;
        
        this.setLayout(new GridLayout(row, column));
        // �¼�����

        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        this.setSize(width, height);

        // ����button ��������
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                viewList.put(new Point(i, j), new Cube());
                this.add(getView(i, j).getView());
            }
        }

        // ���ð�������
        this.addKeyListener(new MyKeyListener(this));

        //�趨�������ֲ�
        JMenuBar jmb = new JMenuBar();
        JMenu jm = new JMenu("��Ϸ");
        JMenuItem item1 = new JMenuItem("����Ϸ");
        item1.addMouseListener(new MyMouseListener(this,Game2048.BUTTON_NEW_GAME));
        JMenuItem item2 = new JMenuItem("�˳�");
        item2.addMouseListener(new MyMouseListener(this, Game2048.BUTTON_EXIT));
        jm.add(item1);
        jm.add(item2);

        JMenu jm2 = new JMenu("����");
        JMenuItem item3 = new JMenuItem("����");
        item3.addMouseListener(new MyMouseListener(this, Game2048.BUTTON_ABOUT));
        jm2.add(item3);

        scoreBoard = new JMenuItem();
        arithmometer = new JMenuItem();
        timer=new JMenuItem();
        
        //�����úõİ�ť����������
        jmb.add(jm);
        jmb.add(jm2);
        jmb.add(scoreBoard);
        jmb.add(arithmometer);
        jmb.add(timer);
        this.setJMenuBar(jmb);
    }

    /**
     * �����ƶ�
     */
    public void up() {
        for (int x = 1; x < row; x++) {
            for (int i = 0; i < column; i++) {
                move(Game2048.MOVE_UP, x, i, true);
           
            }
        }
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
               
    	     getView(x,y).setColor();}
        }
        //������ʾ
        
        createCube();
        for (int x = 1; x < row; x++) {
            for (int i = 0; i < column; i++) {
                move(Game2048.MOVE_UP, x, i, false);
                
            }
        }
        addCount();//���Ӳ���
    }

    /**
     * �����ƶ�
     */
    public void down() {
        for (int x = row - 2; x >= 0; x--) {
            for (int y = 0; y < column; y++) {
                move(Game2048.MOVE_DOWN, x, y, true);
                getView(x,y).setColor();
            }
        }
        
        //������ʾ
        createCube();
        for (int x = row - 2; x >= 0; x--) {
            for (int y = 0; y < column; y++) {
                move(Game2048.MOVE_DOWN, x, y, false);
            }
        }
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
               
    	     getView(x,y).setColor();}
        }
        //���Ӳ���
        addCount();
    }

    /**
     * �����ƶ�
     */
    public void left() {
        for (int y = 1; y < column; y++) {
            for (int x = 0; x < row; x++) {
                move(Game2048.MOVE_LEFT, x, y, true);
               
            }
        }
        //������ʾ
        createCube();
        for (int y = 1; y < column; y++) {
            for (int x = 0; x < row; x++) {
                move(Game2048.MOVE_LEFT, x, y, false);
            }
        }
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
               
    	     getView(x,y).setColor();}
        }
        //���Ӳ���
        addCount();
    }

    /**
     * �����ƶ�
     */
    public void right() {
        for (int y = column - 2; y >= 0; y--) {
            for (int x = 0; x < row; x++) {
                move(Game2048.MOVE_RIGHT, x, y, true);
                getView(x,y).setColor();
            }
        }
        //������ʾ
        createCube();
        for (int y = column - 2; y >= 0; y--) {
            for (int x = 0; x < row; x++) {
                move(Game2048.MOVE_RIGHT, x, y, false);
            }
        }
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
               
    	     getView(x,y).setColor();}
        }
        //���Ӳ���
        addCount();
    }

    /**
     * �ƶ�
     * 
     * @param move_way
     *            �ƶ�����
     * @param x
     *            ������
     * @param y
     *            ������
     */
    private void move(int move_way, int x, int y, boolean isAdd) {
        switch (move_way) {
        case Game2048.MOVE_DOWN: {
            for (; x < row - 1; x++) {
                swap(getView(x + 1, y), getView(x, y), isAdd);
            }
        }
            break;

        case Game2048.MOVE_LEFT: {
            for (; y > 0; y--) {
                swap(getView(x, y - 1), getView(x, y), isAdd);
            }
        }
            break;

        case Game2048.MOVE_RIGHT: {
            for (; y < column - 1; y++) {
                swap(getView(x, y + 1), getView(x, y), isAdd);
            }
        }
            break;

        case Game2048.MOVE_UP: {
            for (; x > 0; x--) {
                swap(getView(x - 1, y), getView(x, y), isAdd);
            }
        }
            break;
        }
    }

    /**
     * ���򽻻�ʵ���ƶ�
     * 
     * @param next
     *            �ƶ���Ŀ��λ��
     * @param now
     *            ��Ҫ�ƶ���Ŀ��
     * @param isAdd
     *            �Ƿ��ǵ�һ���ƶ�
     */
    private void swap(Cube next, Cube now, boolean isAdd) {
        if (isAdd) {
            if (now.getNum() != 0 && next.getNum() == 0) {
                next.setText(now.getNum());
                now.setText(0);
                next.setIsAdded(now.isAdded());
                now.setIsAdded(false);
            } else if (!now.isAdded() && !next.isAdded()
                    && next.getNum() == now.getNum() && now.getNum() != 0) {
                next.setText(now.getNum() * 2);
                now.setText(0);
                next.setIsAdded(true);
                now.setIsAdded(false);
            }
        } else {
            if (next.getNum() == 0) {
                next.setText(now.getNum());
                now.setText(0);
            }
            now.setIsAdded(false);
            next.setIsAdded(false);
        }
    }

    /**
     * ��ȡָ���ؼ�
     * 
     * @param x
     * @param y
     * @return Cube
     */
    private Cube getView(int x, int y) {
        return viewList.get(new Point(x, y));
    }

    /**
     * ��������ؼ� ���λ��
     */
    private void createCube() {
        int x;
        int y;

        do {
            x = (int) (Math.random() * 1000 % row);
            y = (int) (Math.random() * 1000 % column);
        } while (getView(x, y).getNum() != 0);

        getView(x, y).setText(Math.random() > 0.5 ? 2 : 4);
        getView(x, y).setColor();
        isOverGame();
    }

    /**
     * �����Ϸ״̬
     */
    private void isOverGame() {
        int score = 0;
        int state = Game2048.GAME_OVER;
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < column; y++) {
                // ����÷�
                score += getView(x, y).getNum();

                if (getView(x, y).getNum() == 0) {
                    state = Game2048.GAME_CONTINUE;
                } else 
                	if (getView(x, y).getNum() == (task*(gameLv+1)) ) {
                    state = Game2048.GAME_WIN;
                    break;
                }
            }
        }

        if (state != Game2048.GAME_CONTINUE && state != Game2048.GAME_WIN) {
            gameState = Game2048.GAME_OVER;
        } else {
            gameState = state;
        }

        play.run();

        scoreBoard.setText("�÷�:" + score);
        elapsed = System.currentTimeMillis()-Start;  
        long use=elapsed;
        int  minute, second, milli;//��¼���÷֣��룬����  
        milli = (int) (use % 1000);  
        use = use / 1000;  

        second = (int) (use % 60);  
        use = use / 60;  

        minute = (int) (use % 60);    

        timer.setText("��ʱ:"+minute+"'"+second+"''"+milli+"'''");

    }
    public long getTime() {
    	return elapsed;
    }

    /**
     * �Ʋ�
     */
    private void addCount() {
        count++;
        arithmometer.setText("�Ʋ���" + count);
    }

    /**
     * ��ȡ��Ϸ״̬
     * 
     * @return int
     */
    public int getGameState() {
        return gameState;
    }

    /**
     * ��ʼ����Ϸ����
     */
    private void initialise() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                getView(i, j).setText(0);
                getView(i,j).setColor();
            }
        }

        createCube();
        createCube();
        count = 0;
        arithmometer.setText("�Ʋ���" + count);
       // gameLv = 0;
        this.setTitle(task +"*"+ (gameLv + 1));
        timer.setText("  ��ʱ:0'0''0'''");
        Start = System.currentTimeMillis();  
    }

    /**
     * ��������Ϸ
     */
    public void newGame() {
        if (gameState == Game2048.GAME_CONTINUE) {
            int jop = JOptionPane.showConfirmDialog(null, "�Ƿ�ʼ��һ����Ϸ?", "Tips",
                    JOptionPane.YES_NO_OPTION);

            if (jop == JOptionPane.YES_OPTION) {
            	gameLv=0;
                initialise();
                for (int x = 0; x < 4; x++) {
                    for (int y = 0; y < 4; y++) {
                       
            	     getView(x,y).setColor();}
                }
            }
        } else {
        	gameLv=0;
            initialise();
            for (int x = 0; x < 4; x++) {
                for (int y = 0; y < 4; y++) {
                   
        	     getView(x,y).setColor();}
            }
        }
    }

    /**
     * ���ش��ڹر�
     */
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            if (getGameState() == Game2048.GAME_CONTINUE) {
                int jop = JOptionPane.showConfirmDialog(null, "�Ƿ��˳���Ϸ?",
                        "Tips", JOptionPane.YES_NO_OPTION);

                if (jop == JOptionPane.YES_OPTION) {
                    super.processWindowEvent(e);
                }
            } else {
                super.processWindowEvent(e);
            }
        }
    }

    /**
     * ������һ�Ѷ���Ϸ
     */
    public void nextLv() {
        gameLv++;
        //this.setTitle(task * (gameLv + 1)+"");
        initialise();
        }
    
    public int getCount(){
    	return count;
    }

    /**
     * ����
     */
    public void about() {
        JOptionPane.showMessageDialog(null, "����·���ν���һ��A108������");
    }
}

