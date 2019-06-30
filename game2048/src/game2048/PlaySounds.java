package game2048;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

//������������
public class PlaySounds extends Thread {

 private String filename;
 public PlaySounds(String wavfile) {
  filename =wavfile;
 }
 public void run() {
  File soundFile = new File(filename);
  AudioInputStream audioInputStream = null;
  try {
   audioInputStream = AudioSystem.getAudioInputStream(soundFile);// �����Ƶ������
  } catch (Exception e1) {
   e1.printStackTrace();
   return;
  }
  
  AudioFormat format = audioInputStream.getFormat();// ָ�����������ض����ݰ���
  SourceDataLine auline = null;
  DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
  
  try {
   auline = (SourceDataLine) AudioSystem.getLine(info);// �ӻ�Ƶ�����Դ������
   auline.open(format);// �򿪾���ָ����ʽ���У�������ʹ�л�����������ϵͳ��Դ����ÿɲ�����
  } catch (Exception e) {
   e.printStackTrace();
   return;
  }
  auline.start();// ����������ִ������ I/O 
  int nBytesRead = 0;
  //���ǻ���
  byte[] abData = new byte[512];

  try {
   while (nBytesRead != -1) {
    nBytesRead = audioInputStream.read(abData, 0, abData.length);// ����Ƶ����ȡָ������������������ֽڣ����������������ֽ������С�
    if (nBytesRead >= 0)
     auline.write(abData, 0, nBytesRead);// ͨ����Դ�����н���Ƶ����д���Ƶ����
   }
  } catch (IOException e) {
   e.printStackTrace();
   return;
  } finally {
   auline.drain();
   auline.close();
  }
 }
}