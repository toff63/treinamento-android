package com.example.horastrabalhadas;

import com.example.horastrabalhadas.keys.PreferenceKeys;
import com.example.horastrabalhadas.utils.FileUtil;
import com.example.horastrabalhadas.utils.SharedPreferenceUtil;
import com.example.horastrabalhadas.utils.UIFieldAccess;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class LoginListener implements OnClickListener {
	private String name = "gambeiro";
	private String password = "cozinhamais";
	private String FILENAME = "failed_login";
	private UIFieldAccess fieldAccess = new UIFieldAccess();
	private SharedPreferenceUtil pref = new SharedPreferenceUtil();
	private FileUtil file = new FileUtil();
	private Activity ac;
	
	public LoginListener(Activity ac) {
		this.ac = ac;
	}

	@Override
	public void onClick(View v) {
		if(isValid()) storeLoginAndMoveToNextActivity(); else storeInvalidLogin();
	}

	private void storeInvalidLogin() {
		Toast.makeText(ac.getApplicationContext(), "Invalid user name and/or password.", Toast.LENGTH_SHORT).show();
		String fileContent = getFileContent();
		file.write(ac, FILENAME, fileContent);
		Toast.makeText(ac.getApplicationContext(), "Previous login:\n" + fileContent, Toast.LENGTH_SHORT).show();
	}

	private void storeLoginAndMoveToNextActivity() {
		storeLoginPasswordForNextLogin(fieldAccess.getTextById(ac, R.id.nome), fieldAccess.getTextById(ac, R.id.senha));
		ac.finish();
		ac.startActivity(getIntent(fieldAccess.getTextById(ac, R.id.nome), fieldAccess.getTextById(ac, R.id.senha)));
	}
	
	private String getFileContent(){
		String fileContent = file.read(ac, FILENAME);
		String content = fieldAccess.getTextById(ac, R.id.nome) + ":" + fieldAccess.getTextById(ac, R.id.senha) + "\n";
		return  fileContent.concat(content);
	}


	private boolean isValid() {
		return name.equals(fieldAccess.getTextById(ac, R.id.nome)) && password.equals( fieldAccess.getTextById(ac, R.id.senha));
	}

	private void storeLoginPasswordForNextLogin(String name, String password) {
		pref.addString(ac, name, PreferenceKeys.NAME);
		pref.addString(ac, password, PreferenceKeys.PASSWORD);
	}

	private Intent getIntent(String name, String password) {
		Intent intent = new Intent(ac.getBaseContext(), FormResultActivity.class);
		intent.putExtra("name", name);
		intent.putExtra("senha", password);
		return intent;
	}
}
