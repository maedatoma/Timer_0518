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
	JTextField tfield_Min;		// "分"入力欄
	JLabel text_Colon;
	SpinnerNumberModel model;	// スピナーの設定
	JSpinner jspin_Sec;			// "秒"スピナー
	JTextField jspin_Sec_setting;
	JLabel label_Time;			// 時間の表示場所
	JLabel label_Fin;			// 終了メッセージ
	JLabel label_Img;
	ImageIcon icon;
	int min;					// 分
	int sec;					// 秒
	int get_Min;				// 入力された"分"を取得する変数
	int get_Sec;				// 選択された"秒"を取得する変数
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
		label_Time = new JLabel();
		label_Time.setFont( new Font("", Font.BOLD, 30 ) );
		label_Fin = new JLabel();

		// テキストフィールド(入力欄)を生成
		tfield_Min = new JTextField("3");
		tfield_Min.setColumns( 2 );		// 入力欄の大きさ
		text_Colon = new JLabel(":");
		model = new SpinnerNumberModel( 0, 0, 59, 1 );
		jspin_Sec = new JSpinner( model );
		jspin_Sec.setPreferredSize( new Dimension( 40, 20 ) );	// 大きさ
		jspin_Sec_setting = ( ( JSpinner.NumberEditor ) jspin_Sec.getEditor() ).getTextField();
		jspin_Sec_setting.setEditable(false);

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
			panel_Title.add( jspin_Sec );

			// 時間表示 panel
			panel_TimeLabel = new JPanel();
			panel_TimeLabel.add( label_Time );

			// 画像表示 panel
			panel_Img = new JPanel();
			// アイコンの設定
			label_Img = new JLabel();
			icon = new ImageIcon("img/cup_noodle.png");
			label_Img.setIcon( icon );
			panel_Img.add( label_Img );

			// ボタン panel
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
			try{
				// 入力された分を取得する
				get_Min = Integer.parseInt( tfield_Min.getText() );
				// 入力された秒を取得する
				get_Sec = (Integer)model.getValue();
				// 分を指定
				min = 0;
				// 秒を指定
				sec = 0;
				// タイマーをスタートさせる
				timer.start();
				// ボタン,入力欄を押せないようにする
				btn_Start.setEnabled(false);
				tfield_Min.setEnabled(false);
				jspin_Sec.setEnabled(false);
				// 時間を表示
				label_Time.setText( min + ":" + "0" + sec );
				// 終了メッセージを削除
				label_Fin.setText("");
			} catch( NumberFormatException e ){
				JOptionPane.showMessageDialog( this, "数値を入力してください");
			}
		}

		// 時間のカウントの処理
		if( ae_Cmd.equals("timer") ){
			// 時間を表示
			label_Time.setText( min + ":" + sec );
			// 10秒未満の時、10の位に"0"を加える
			if( sec < 10 ){
				label_Time.setText( min + ":" + "0" + sec );
			}
			// "秒"を増やす
			sec++;
		}

		// 60秒( 1分 )経過したら( 入力した"分"が"1"以上の時 )
		if( sec >= 60 && get_Min >= 1 ){
			// "秒"を "0" に戻す
			sec = 0;
			// "分"を増やす
			min++;
		}

		// 設定した時間が経過した時( 入力した"分"が"0"だった時 )
		if( get_Min == min && sec - get_Sec == 1 ){
			// タイマーを停止
			timer.stop();
			// 終了メッセージの表示
			label_Fin.setText( get_Min + "分" + get_Sec + "秒経過しました");
			// ボタン,入力欄を押せるようにする
			btn_Start.setEnabled(true);
			tfield_Min.setEnabled(true);
			jspin_Sec.setEnabled(true);
			// ボタンの文字を変更
			btn_Start.setText("もう一度");

		// 設定した時間が経過した時( 入力した"分"が"1"以上だった時 )
		} else if( get_Min < min && sec < get_Sec - 1 ){
			// タイマーを停止
			timer.stop();
			// 終了メッセージの表示
			label_Fin.setText( get_Min + "分" + get_Sec + "秒経過しました");
			// ボタン,入力欄を押せるようにする
			btn_Start.setEnabled(true);
			tfield_Min.setEnabled(true);
			jspin_Sec.setEnabled(true);
			// ボタンの文字を変更
			btn_Start.setText("もう一度");
		}
	}
}