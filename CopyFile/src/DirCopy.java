import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DirCopy {
	static int count = 0;

	public static void main(String[] args) {
		copyDir("D:/�߼��ܽ�.txt", "D:");
	}

	/**
	 * �����ļ�
	 */
	public static void copyFile(String srcFile, String destFile) {
		File file = new File(srcFile);
		if (!file.exists()) {
			System.out.println("�ļ������ڣ�������ѡ��Ҫ���Ƶ��ļ�!");
		} else {
			try {
				// �ļ��ֽ���
				FileInputStream fis = new FileInputStream(file);
				FileOutputStream fos = new FileOutputStream(destFile);
				int i;
				while ((i = fis.read()) != -1) {
					fos.write(i);
				}
				fos.flush();
				fos.close();
				fis.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * �����ļ���
	 * 							d:/test.txt			d:/test.txt
	 */								
	public static void copyDir(String srcPath, String destPath) {
		File srcFile = new File(srcPath);
		if (!srcFile.exists()) {
			System.out.println("��ѡ����Ч���ļ�·��");
		} else {
			if (srcFile.isFile()) {
				if(count==0){
					destPath = destPath + srcFile.separator +"����-"+ srcFile.getName();
					count++;
				}else{
					destPath = destPath + srcFile.separator + srcFile.getName();
				}
				copyFile(srcPath, destPath);
			} else {
				// ������ļ���
				if (count == 0) {
					destPath = destPath + srcFile.separator + srcFile.getName()
							+ "-����";
					count++;
					// �����ļ���
				} else {
					destPath = destPath + srcFile.separator + srcFile.getName();
				}
				File destFile = new File(destPath);
				destFile.mkdirs();

				File[] files = srcFile.listFiles();
				for (File f : files) {
					copyDir(f.getAbsolutePath(), destPath);
				}

			}
		}
	}

}
