// Timer_toma.java
/* 要件
	- 3分間の時間を計測するタイマーを作る( Timer )
		- 必要な機能
			- スタートボタン
			- 時間をカウント
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Timer_toma extends JFrame implements ActionListener{
	// field
	Timer timer;
	JPanel panel_Main;		// メイン panel
	JPanel panel_Label;		// ラベル panel
	JPanel panel_Button;	// ボタン panel
	JLabel label_Title;
	JLabel label_Time;
	JLabel label_Fin;		// 終了メッセージ
	JLabel label_Img;
	ImageIcon icon;
	int sec;				// 秒
	int min;				// 分
	JButton btn_Start;

	// method
	public static void main(String[] args) {
		// window 関連
		Timer_toma frame_Main = new Timer_toma();

		// window の名前
		frame_Main.setTitle("ラーメンTimer");
		// window の表示場所
		frame_Main.setLocation( 512, 200 );
		// window の大きさ
		frame_Main.setSize( 256, 200 );
		// window の大きさ固定
		frame_Main.setResizable(false);
		// 閉じるクリックで処理の終了
		frame_Main.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		// window の表示
		frame_Main.setVisible(true);
	}

	public Timer_toma(){
		label_Title = new JLabel("ラーメンTimer");
		label_Time = new JLabel("ボタンを押して下さい");
		label_Fin = new JLabel();

		// ボタンを生成
		btn_Start = new JButton("Start");
		btn_Start.setActionCommand("start");
		btn_Start.addActionListener(this);

		// パネルを生成
		panel_Main = new JPanel();
		panel_Main.add( label_Title );

		panel_Label = new JPanel();
		panel_Label.add( label_Time );

		// アイコンの設定
		label_Img = new JLabel();
		icon = new ImageIcon("img/cup_noodle.png");
		label_Img.setIcon( icon );
		panel_Label.add( label_Img );

		panel_Button = new JPanel();
		panel_Button.add( btn_Start );
		panel_Button.add( label_Fin );

		// タイマーを生成
		timer = new Timer( 1000, this );
		timer.setActionCommand("timer");

		// window に 各panel を載せる
		getContentPane().add( panel_Main, BorderLayout.PAGE_START );
		getContentPane().add( panel_Label, BorderLayout.CENTER );
		getContentPane().add( panel_Button, BorderLayout.SOUTH );
	}

	public void actionPerformed( ActionEvent ae ){
		// command を調べる
		String ae_Cmd = ae.getActionCommand();

		// ボタンが押された時の処理
		if( ae_Cmd.equals("start") ){
			// 秒を指定
			sec = 0;
			// 分を指定
			min = 0;
			// タイマーをスタートさせる
			timer.start();
			// ボタンを押せないようにする
			btn_Start.setEnabled(false);
			// 時間を表示
			label_Time.setText( min + "分" + "0" + sec + "秒" );
			// 終了メッセージを削除
			label_Fin.setText("");
		}

		// 時間のカウントの処理
		if( ae_Cmd.equals("timer") ){
			// 時間を表示
			label_Time.setText( min + "分" + sec + "秒" );
			// 10秒未満の時、10の位に"0"を加える
			if( sec < 10 ){
				label_Time.setText( min + "分" + "0" + sec + "秒" );
			}
			// 秒を増やす
			sec++;
		}

		// 60秒( 1分 )経過したら
		if( sec >= 60 ){
			// 秒を "0" に戻す
			sec = 0;
			// 分を増やす
			min++;
		}

		// 3分経過したら
		if( min >= 3 && sec == 1 ){
			// 終了メッセージの表示
			label_Fin.setText( min + "分経過しました");
			// タイマーを停止
			timer.stop();
			// ボタンを押せるようにする
			btn_Start.setEnabled(true);
		}
	}
}