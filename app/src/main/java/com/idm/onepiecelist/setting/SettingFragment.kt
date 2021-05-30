package com.idm.onepiecelist.setting

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import com.idm.onepiecelist.databinding.FragmentSettingBinding
import com.idm.onepiecelist.utils.alarm.AlarmReceiver

class SettingFragment : Fragment() {

    private lateinit var binding: FragmentSettingBinding
    private val switchPreference = "switch preference"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val alarmReceiver = AlarmReceiver()
        val time = "09:00"
        val message = "Open your App Today"
        val preferences =
            this.getActivity()
                ?.getSharedPreferences(switchPreference, AppCompatActivity.MODE_PRIVATE)
        val editPref = preferences?.edit()
        if (preferences != null) {
            binding.switchAlarm.setChecked(preferences.getBoolean("isChecked", false))
        }
        with(binding) {


            btnChangeLanguage.setOnClickListener {
                startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
            }

            switchAlarm.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    editPref?.putBoolean("isChecked", true)
                    alarmReceiver.setRepeatingAlarm(requireActivity(), time, message)
                } else {
                    editPref?.putBoolean("isChecked", false)
                    alarmReceiver.cancelAlarm(requireActivity())
                }
                editPref?.apply()
            }
            )
        }
    }


}