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
	JPanel panel_Title;			// タイトル panel
	JPanel panel_TimeLabel;		// 時間表示 panel
	JPanel panel_Button;		// ボタン panel
	JPanel panel_Img;			// 画像表示 panel
	JLabel label_Title;
	JTextField tfield_Min;		// 分入力欄
	JLabel text_Colon;
	JLabel label_Sec;
	JLabel label_Time;			// 時間の表示場所
	JLabel label_Fin;			// 終了メッセージ
	JLabel label_Img;
	ImageIcon icon;
	int sec;					// 秒
	int min;					// 分
	int get_Min;				// 入力された分を取得する変数
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
		frame_Main.setSize( 400, 200 );
		// window の大きさ固定
		frame_Main.setResizable(false);
		// 閉じるクリックで処理の終了
		frame_Main.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		// window の表示
		frame_Main.setVisible(true);
	}

	public Timer_toma(){
		// ラベルを生成
		label_Title = new JLabel("時間を入力してください(半角数字のみ)→");
		label_Time = new JLabel("0:00");
		label_Time.setFont( new Font("", Font.BOLD, 30 ) );
		label_Fin = new JLabel();

		// テキストフィールド(入力欄)を生成
		tfield_Min = new JTextField("3");
		tfield_Min.setColumns( 2 );		// 入力欄の大きさ
		text_Colon = new JLabel(":");
		label_Sec = new JLabel("00");

		// ボタンを生成
		btn_Start = new JButton("Start");
		btn_Start.setActionCommand("start");
		btn_Start.addActionListener(this);

		// パネルを生成
			// タイトル panel
			panel_Title = new JPanel();
			panel_Title.add( label_Title );
			panel_Title.add( tfield_Min );
			panel_Title.add( text_Colon );
			panel_Title.add( label_Sec );

			// 時間表示 panel
			panel_TimeLabel = new JPanel();
			panel_TimeLabel.add( label_Time );

		// アイコンの設定
		panel_Img = new JPanel();
		label_Img = new JLabel();
		icon = new ImageIcon("img/cup_noodle.png");
		label_Img.setIcon( icon );
		panel_Img.add( label_Img );

		panel_Button = new JPanel();
		panel_Button.add( btn_Start );
		panel_Button.add( label_Fin );

		// タイマーを生成
		timer = new Timer( 1000, this );
		timer.setActionCommand("timer");

		// window に 各panel を載せる
		getContentPane().add( panel_Title, BorderLayout.NORTH );
		getContentPane().add( panel_TimeLabel, BorderLayout.CENTER );
		getContentPane().add( panel_Img, BorderLayout.EAST );
		getContentPane().add( panel_Button, BorderLayout.SOUTH );
	}

	public void actionPerformed( ActionEvent ae ){
		// command を調べる
		String ae_Cmd = ae.getActionCommand();

		// ボタンが押された時の処理
		if( ae_Cmd.equals("start") ){
			// 秒を指定
			sec = 0;
			// 入力された分を取得する
			get_Min = Integer.parseInt( tfield_Min.getText() );
			// 分を指定
			min = 0;
			// タイマーをスタートさせる
			timer.start();
			// ボタン,入力欄を押せないようにする
			btn_Start.setEnabled(false);
			tfield_Min.setEnabled(false);
			// 時間を表示
			label_Time.setText( min + ":" + "0" + sec );
			// 終了メッセージを削除
			label_Fin.setText("");
		}

		// 時間のカウントの処理
		if( ae_Cmd.equals("timer") ){
			// 時間を表示
			label_Time.setText( min + ":" + sec );
			// 10秒未満の時、10の位に"0"を加える
			if( sec < 10 ){
				label_Time.setText( min + ":" + "0" + sec );
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

		// 入力した分だけ時間が経過したら
		if( min >= get_Min && sec == 1 ){
			// 終了メッセージの表示
			label_Fin.setText( get_Min + "分経過しました");
			// タイマーを停止
			timer.stop();
			// ボタン,入力欄を押せるようにする
			btn_Start.setEnabled(true);
			tfield_Min.setEnabled(true);
		}
	}
}