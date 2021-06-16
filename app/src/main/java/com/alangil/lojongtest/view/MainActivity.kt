package com.alangil.lojongtest.view

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ScrollView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.alangil.lojongtest.R
import com.alangil.lojongtest.service.repository.remote.RequisitarAPI
import com.alangil.lojongtest.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_view.view.*
import kotlinx.coroutines.*
import java.util.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    /**
     * Definir lateinit viewModel
     */
    private lateinit var mViewModel: MainActivityViewModel

    /**
     * Coroutine para delay ao mover elefante
     */
    // Verificador de status
    var step1IsChecked: Boolean = false
    var step2IsChecked: Boolean = false
    var step3IsChecked: Boolean = false
    var step4IsChecked: Boolean = false
    var step5IsChecked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Remover ActionBar
         */
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        /**
         * Iniciar scroll no fim da tela
         */
        scroll_view.post(Runnable { scroll_view.fullScroll(ScrollView.FOCUS_DOWN) })

        /**
         * Definir a classe para ser escutada pela ViewModel
         */
        mViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        carregarApi()
        setListeners()
        observer()

    }

    private fun carregarApi() {

        /**
         * Função de carregar API
         * criada utilizando BroadCast e AlarmManager
         * para que ocorra a cada 24 horas
         */

        val calendar: Calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 4)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)

        val tarefaIntent = Intent(this, RequisitarAPI::class.java)
        val tarefaPendingIntent = PendingIntent.getBroadcast(this, 1234, tarefaIntent, 0)

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP, calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY, tarefaPendingIntent
        )
    }

    /**
     * Observer para atualização dos dados dos Steps
     */

    private fun observer() {

        val view = View.inflate(this, R.layout.dialog_view, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(view)
        val dialog = builder.create()


        mViewModel.loadFrase.observe(this, {
            if (it != null) {
                view.text_frase.text = it.text
            }
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            view.btn_confirmar.setOnClickListener {
                dialog.dismiss()
            }
        })
    }

    /**
     * Listeners para clicks nos steps
     */

    private fun setListeners() {
        img_23.setOnClickListener(this)
        img_20.setOnClickListener(this)
        img_14.setOnClickListener(this)
        img_11.setOnClickListener(this)
        img_8.setOnClickListener(this)
    }


    override fun onClick(v: View) {

        var step_1: LottieAnimationView = findViewById(R.id.step_1)
        var step_2: LottieAnimationView = findViewById(R.id.step_2)
        var step_3: LottieAnimationView = findViewById(R.id.step_3)
        var step_4: LottieAnimationView = findViewById(R.id.step_4)
        var step_5: LottieAnimationView = findViewById(R.id.step_5)


        val id = v.id
        var fraseId = 0


        // Atividade Step 1
        if (id == R.id.img_23) {


            step_1.speed = 1f
            step_1.visibility = View.VISIBLE
            step_2.visibility = View.INVISIBLE
            step2IsChecked = false
            step_3.visibility = View.INVISIBLE
            step3IsChecked = false
            step_4.visibility = View.INVISIBLE
            step4IsChecked = false
            step_5.visibility = View.INVISIBLE
            step5IsChecked = false
            step_1.playAnimation()
            step1IsChecked = true


            fraseId = 1


        }

        // Atividade Step 2

        else if (id == R.id.img_20) {
            step_2.speed = 1f
            step_2.visibility = View.VISIBLE
            step_1.visibility = View.INVISIBLE
            step1IsChecked = false
            step_3.visibility = View.INVISIBLE
            step3IsChecked = false
            step_4.visibility = View.INVISIBLE
            step4IsChecked = false
            step_5.visibility = View.INVISIBLE
            step5IsChecked = false
            step_2.playAnimation()
            step2IsChecked = true

            fraseId = 2
        }

        // Atividade Step 3


        else if (id == R.id.img_14) {

            step_3.speed = 1f
            step_3.visibility = View.VISIBLE
            step_1.visibility = View.INVISIBLE
            step1IsChecked = false
            step_2.visibility = View.INVISIBLE
            step2IsChecked = false
            step_4.visibility = View.INVISIBLE
            step4IsChecked = false
            step_5.visibility = View.INVISIBLE
            step5IsChecked = false
            step_3.playAnimation()
            step3IsChecked = true

            fraseId = 3
        }

        // Atividade Step 4
        else if (id == R.id.img_11) {


            step_4.speed = 1f
            step_4.visibility = View.VISIBLE
            step_1.visibility = View.INVISIBLE
            step1IsChecked = false
            step_2.visibility = View.INVISIBLE
            step2IsChecked = false
            step_3.visibility = View.INVISIBLE
            step3IsChecked = false
            step_5.visibility = View.INVISIBLE
            step5IsChecked = false
            step_4.playAnimation()
            step4IsChecked = true

            fraseId = 4
        }

        // Atividade Step 5
        else if (id == R.id.img_8) {

            step_5.speed = 1f
            step_5.visibility = View.VISIBLE
            step_1.visibility = View.INVISIBLE
            step1IsChecked = false
            step_2.visibility = View.INVISIBLE
            step2IsChecked = false
            step_3.visibility = View.INVISIBLE
            step3IsChecked = false
            step_4.visibility = View.INVISIBLE
            step4IsChecked = false
            step_5.playAnimation()
            step5IsChecked = true

            fraseId = 5
        }

        // Carregar frase correspondente ao step
        mViewModel.load(fraseId)

    }



}

