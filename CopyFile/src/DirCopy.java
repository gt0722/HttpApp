import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DirCopy {
	static int count = 0;

	public static void main(String[] args) {
		copyDir("D:/高级总结.txt", "D:");
	}

	/**
	 * 复制文件
	 */
	public static void copyFile(String srcFile, String destFile) {
		File file = new File(srcFile);
		if (!file.exists()) {
			System.out.println("文件不存在，请重新选择要复制的文件!");
		} else {
			try {
				// 文件字节流
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
	 * 复制文件夹
	 * 							d:/test.txt			d:/test.txt
	 */								
	public static void copyDir(String srcPath, String destPath) {
		File srcFile = new File(srcPath);
		if (!srcFile.exists()) {
			System.out.println("请选择有效的文件路径");
		} else {
			if (srcFile.isFile()) {
				if(count==0){
					destPath = destPath + srcFile.separator +"副本-"+ srcFile.getName();
					count++;
				}else{
					destPath = destPath + srcFile.separator + srcFile.getName();
				}
				copyFile(srcPath, destPath);
			} else {
				// 如果是文件夹
				if (count == 0) {
					destPath = destPath + srcFile.separator + srcFile.getName()
							+ "-副本";
					count++;
					// 创建文件夹
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
