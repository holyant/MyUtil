package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Checkcode extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nums = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		// 第一步，生成一张图片
		// 1,创建一个内存映像对象
		BufferedImage image = new BufferedImage(60, 20, BufferedImage.TYPE_INT_RGB);
		// 2,获得画笔
		Graphics g = image.getGraphics();
		// 3,给笔上颜色(随机的一个颜色)
		Random r = new Random();
		g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
		// 4,设置背景颜色
		g.fillRect(0, 0, 60, 20);
		// 5,绘图
//		String number = r.nextInt(99999) + "";
		String number = "";
		for(int i=0;i<5;i++){
			number += nums.charAt(r.nextInt(nums.length()));
		}
		// 将随机数绑订到session对象上
		HttpSession session = request.getSession();
		session.setAttribute("number", number);
		g.setColor(new Color(0, 0, 0));
		g.drawString(number, 10, 15);
		// 加干扰线
		// g.drawLine(x1, y1, x2,y2);
		g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		g.drawLine(r.nextInt(60), r.nextInt(20), r.nextInt(60), r.nextInt(20));
		// 第二步，将图片压缩，并输出到客户端
		// 1,设置正确的消息头，告诉浏览器返回的
		// 是图片。
		response.setContentType("image/jpeg");
		// 2,获得一个字节输出流。
		OutputStream ops = response.getOutputStream();
		// 3,压缩并输出
		javax.imageio.ImageIO.write(image, "jpeg", ops);

	}
	public static void main(String[] args){
		String[] nums = {"A","B","C","D","E","F","G","H","I","J","K",
				"L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
				"0","1","2","3","4","5","6","7","8","9"};
		Random r = new Random();
		String number = "";
		for(int i=0;i<5;i++){
			number+=nums[r.nextInt(36)];
		}
		System.out.println(number);
	}
}
