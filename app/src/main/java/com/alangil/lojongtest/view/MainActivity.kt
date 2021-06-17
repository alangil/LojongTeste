package com.alangil.lojongtest.view

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.alangil.lojongtest.R
import com.alangil.lojongtest.service.model.StateModel
import com.alangil.lojongtest.service.repository.local.FraseRepository
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
    private var fraseId = 0

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
         * Definir a classe para ser escutada pela ViewModel
         */
        mViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        var stateVerify = mViewModel.verificaStates()
        var stateBDVerify = mViewModel.verificaStateBD()

        setListeners()
        observer()

        if (stateBDVerify == 0) {
            carregarApi()

        }

        /**
         * Verifica ultimo state do usuário
         */

        if (stateVerify == 0) {
            val state1 = StateModel().apply {
                this.state = true
            }
            step_1.visibility = View.VISIBLE
            scroll_view.post(Runnable { scroll_view.fullScroll(ScrollView.FOCUS_DOWN) })
            mViewModel.saveState(state1)

            val state2 = StateModel().apply {
                this.state = false
            }
            mViewModel.saveState(state2)
            val state3 = StateModel().apply {
                this.state = false
            }
            mViewModel.saveState(state3)

            val state4 = StateModel().apply {
                this.state = false
            }
            mViewModel.saveState(state4)

            val state5 = StateModel().apply {
                this.state = false
            }
            mViewModel.saveState(state5)
        } else if (stateVerify == 1) {
            val state1 = StateModel().apply {
                this.state = true
            }
            step_1.visibility = View.VISIBLE
            scroll_view.post(Runnable { scroll_view.fullScroll(ScrollView.FOCUS_DOWN) })
            mViewModel.saveState(state1)
        } else if (stateVerify == 2) {
            val state2 = StateModel().apply {
                this.state = true
            }
            step_2.visibility = View.VISIBLE
            mViewModel.saveState(state2)
            scroll_view.post(Runnable { scroll_view.fullScroll(ScrollView.FOCUS_DOWN) })
        } else if (stateVerify == 3) {
            val state3 = StateModel().apply {
                this.state = true
            }
            step_3.visibility = View.VISIBLE
            scroll_view.post(Runnable { scroll_view.fullScroll(ScrollView.FOCUS_DOWN) })
            mViewModel.saveState(state3)
        } else if (stateVerify == 4) {
            val state4 = StateModel().apply {
                this.state = true
            }
            step_4.visibility = View.VISIBLE
            scroll_view.post(Runnable { scroll_view.fullScroll(ScrollView.FOCUS_UP) })
            mViewModel.saveState(state4)
        } else if (stateVerify == 5) {
            val state5 = StateModel().apply {
                this.state = true
            }
            step_5.visibility = View.VISIBLE
            scroll_view.post(Runnable { scroll_view.fullScroll(ScrollView.FOCUS_UP) })
            mViewModel.saveState(state5)

        }

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
                dialog.show()
                dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                view.btn_confirmar.setOnClickListener {
                    dialog.dismiss()
                }
            }

        })


    }

    private fun carregarApi() {

        /**
         * Função de carregar API
         * criada utilizando BroadCast e AlarmManager
         * para que ocorra a cada 24 horas
         * e faça a primeira população do BD de frases
         */
        val mFraseRepository = FraseRepository(this)
        mFraseRepository.popularBD()
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


        // Atividade Step 1
        if (id == R.id.img_23) {


            step_1.speed = 1f
            step_1.visibility = View.VISIBLE
            step_2.visibility = View.INVISIBLE
            step2IsChecked = false
            mViewModel.updateState(2, step2IsChecked)
            step_3.visibility = View.INVISIBLE
            step3IsChecked = false
            mViewModel.updateState(3, step3IsChecked)
            step_4.visibility = View.INVISIBLE
            step4IsChecked = false
            mViewModel.updateState(4, step4IsChecked)
            step_5.visibility = View.INVISIBLE
            step5IsChecked = false
            mViewModel.updateState(5, step5IsChecked)
            step_1.playAnimation()
            step1IsChecked = true
            mViewModel.updateState(1, step1IsChecked)


            fraseId = 1


        }

        // Atividade Step 2

        else if (id == R.id.img_20) {
            step_2.speed = 1f
            step_2.visibility = View.VISIBLE
            step_1.visibility = View.INVISIBLE
            step1IsChecked = false
            mViewModel.updateState(1, step1IsChecked)
            step_3.visibility = View.INVISIBLE
            step3IsChecked = false
            mViewModel.updateState(3, step3IsChecked)
            step_4.visibility = View.INVISIBLE
            step4IsChecked = false
            mViewModel.updateState(4, step4IsChecked)
            step_5.visibility = View.INVISIBLE
            step5IsChecked = false
            mViewModel.updateState(5, step5IsChecked)
            step_2.playAnimation()
            step2IsChecked = true
            mViewModel.updateState(2, step2IsChecked)

            fraseId = 2
        }

        // Atividade Step 3


        else if (id == R.id.img_14) {

            step_3.speed = 1f
            step_3.visibility = View.VISIBLE
            step_1.visibility = View.INVISIBLE
            step1IsChecked = false
            mViewModel.updateState(1, step1IsChecked)
            step_2.visibility = View.INVISIBLE
            step2IsChecked = false
            mViewModel.updateState(2, step2IsChecked)
            step_4.visibility = View.INVISIBLE
            step4IsChecked = false
            mViewModel.updateState(4, step4IsChecked)
            step_5.visibility = View.INVISIBLE
            step5IsChecked = false
            mViewModel.updateState(5, step5IsChecked)
            step_3.playAnimation()
            step3IsChecked = true
            mViewModel.updateState(3, step3IsChecked)

            fraseId = 3
        }

        // Atividade Step 4
        else if (id == R.id.img_11) {


            step_4.speed = 1f
            step_4.visibility = View.VISIBLE
            step_1.visibility = View.INVISIBLE
            step1IsChecked = false
            mViewModel.updateState(1, step1IsChecked)
            step_2.visibility = View.INVISIBLE
            step2IsChecked = false
            mViewModel.updateState(2, step2IsChecked)
            step_3.visibility = View.INVISIBLE
            step3IsChecked = false
            mViewModel.updateState(3, step3IsChecked)
            step_5.visibility = View.INVISIBLE
            step5IsChecked = false
            mViewModel.updateState(5, step5IsChecked)
            step_4.playAnimation()
            step4IsChecked = true
            mViewModel.updateState(4, step4IsChecked)

            fraseId = 4
        }

        // Atividade Step 5
        else if (id == R.id.img_8) {

            step_5.speed = 1f
            step_5.visibility = View.VISIBLE
            step_1.visibility = View.INVISIBLE
            step1IsChecked = false
            mViewModel.updateState(1, step1IsChecked)
            step_2.visibility = View.INVISIBLE
            step2IsChecked = false
            mViewModel.updateState(2, step2IsChecked)
            step_3.visibility = View.INVISIBLE
            step3IsChecked = false
            mViewModel.updateState(3, step3IsChecked)
            step_4.visibility = View.INVISIBLE
            step4IsChecked = false
            mViewModel.updateState(4, step4IsChecked)
            step_5.playAnimation()
            step5IsChecked = true
            mViewModel.updateState(5, step5IsChecked)

            fraseId = 5
        }

        // Carregar frase correspondente ao step
        mViewModel.load(fraseId)

    }
}

