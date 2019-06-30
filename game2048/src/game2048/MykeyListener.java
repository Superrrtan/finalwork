package game2048;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

class MyKeyListener implements KeyListener {
    /*
     * ���̴��� w/87 s/83 a/65 d/68 up/38 down/40 left/37 right/39 f1/112 f2/113
     * f3/114
     */
    final public static int KEY_W = 0xf57;
    final public static int KEY_S = 0xf53;
    final public static int KEY_A = 0xf41;
    final public static int KEY_D = 0xf44;
    final public static int KEY_UP = 0xf26;
    final public static int KEY_DOWN = 0xf28;
    final public static int KEY_LEFT = 0xf25;
    final public static int KEY_RIGHT = 0xf27;

    private Game2048 game;

    /**
     * ����һ�����̼�����
     * 
     * @param game
     *            ������
     */
    public MyKeyListener(Game2048 game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode() + 0xf00;

        switch (game.getGameState()) {
        case Game2048.GAME_CONTINUE: {
            switch (keyCode) {
            case MyKeyListener.KEY_W:
            case MyKeyListener.KEY_UP: {
                game.up();
            }
                break;

            case MyKeyListener.KEY_S:
            case MyKeyListener.KEY_DOWN: {
                game.down();
            }
                break;

            case MyKeyListener.KEY_A:
            case MyKeyListener.KEY_LEFT: {
                game.left();
            }
                break;

            case MyKeyListener.KEY_D:
            case MyKeyListener.KEY_RIGHT: {
                game.right();
            }
                break;
            }
        }
            break;

        case Game2048.GAME_OVER: {
        	float time=game.getTime()/1000;
        	float n=(float)(Math.round((time/game.getCount())*10)/10);
            int jop = JOptionPane
                    .showConfirmDialog(null, "���ź�����û�ܴ�ɱ���Ŀ��\n���ƽ�������ٶ�Ϊ"+n+"s/��"+"\n�Ƿ�������Ϸ?","��Ϸ����",
                            JOptionPane.YES_NO_OPTION);
            if (jop == JOptionPane.YES_OPTION) {
                game.newGame();
            }else game.dispose();
        }
            break;

        case Game2048.GAME_WIN: {
        	float time=game.getTime()/1000;
        	float n=(float)(Math.round((time/game.getCount())*10)/10);
            int jop = JOptionPane.showConfirmDialog(null,
                    "������ɱ���Ŀ��:" + game.getTitle() + "���ƽ�������ٶ�Ϊ" +n+"s/��"+"\n�Ƿ������Ϸ?", "��ϲ����",
                    JOptionPane.YES_NO_OPTION);

            if (jop == JOptionPane.YES_OPTION) {
                game.nextLv();
            }
        }
            break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
