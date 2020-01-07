package com.example.tic_tac_toe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var PLAYER = true
    var turn_count=0
    var boardstatus = Array(3){IntArray(3)}
    lateinit var board :  Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = arrayOf(
            arrayOf(button1,button2,button3),
            arrayOf(button4,button5,button6),
            arrayOf(button7,button8,button9)
        )

        for(i in board)
        {
            for(button in i )
            {
                button.setOnClickListener(this)
            }
        }
        initializeboardstatus()
    }
    private fun initializeboardstatus()
    {
        for(i in 0..2)
        {
            for(j in 0..2)
            {
                boardstatus[i][j]=-1
            }
        }

    for (i in board) {
        for (button in i) {
            button.isEnabled = true
            button.text = ""

        }
    }
}
    override fun onClick(view:View){
        when(view.id)
        {
            R.id.button1 -> updatevalue(row=0, col=0 , player= PLAYER)
            R.id.button2 -> updatevalue(row=0, col=1 , player= PLAYER)
            R.id.button3 -> updatevalue(row=0, col=2 , player= PLAYER)
            R.id.button4 -> updatevalue(row=1, col=0 , player= PLAYER)
            R.id.button5 -> updatevalue(row=1, col=1 , player= PLAYER)
            R.id.button6 -> updatevalue(row=1, col=2 , player= PLAYER)
            R.id.button7 -> updatevalue(row=2, col=0 , player= PLAYER)
            R.id.button8 -> updatevalue(row=2, col=1 , player= PLAYER)
            R.id.button9 -> updatevalue(row=2, col=2 , player= PLAYER)
        }
        PLAYER=!PLAYER
        turn_count++
        if(PLAYER)
        {
            updatedisplay("Player X turn ")
        }
        else
        {
            updatedisplay("Player O Turn")
        }
        if(turn_count==9) {
            updatedisplay("Game Draw!!1")
        }
        checkwinner()
    }
        private fun checkwinner(){
            //horizantal rows
            for(i in 0..2)
            {
                if(boardstatus[i][0] == boardstatus[i][1] && boardstatus[i][0] == boardstatus[i][2])
                {
                    if(boardstatus[i][0]==1)
                    {
                        updatedisplay("Player X Winner")
                        break
                    }
                    else if(boardstatus[i][0]==0)
                    {
                        updatedisplay("Player 0 Winner")
                        break
                    }
                }
            }
            //vertical rows
            for(i in 0..2)
            {
                if(boardstatus[0][i] == boardstatus[1][i] && boardstatus[0][i] == boardstatus[2][i])
                {
                    if(boardstatus[0][i]==1)
                    {
                        updatedisplay("Player X Winner")
                        break
                    }
                    else if(boardstatus[0][i]==0)
                    {
                        updatedisplay("Player 0 Winner")
                        break
                    }
                }
            }
            if(boardstatus[0][0] == boardstatus[1][1] && boardstatus[0][0] == boardstatus[2][2])
            {
                if(boardstatus[0][0]==1)
                {
                    updatedisplay("Player X Winner")
                }
                else if(boardstatus[0][0]==0)
                {
                    updatedisplay("Player 0 Winner")

                }
            }
            if(boardstatus[0][2] == boardstatus[1][1] && boardstatus[0][2] == boardstatus[2][0])
            {
                if(boardstatus[0][2]==1)
                {
                    updatedisplay("Player X Winner")
                }
                else if(boardstatus[0][2]==0)
                {
                    updatedisplay("Player 0 Winner")

                }
            }
        }
       private fun updatedisplay(text:String )
        {
            displayTV.text=text
            if(text.contains("Winner"))
            {
                disablebutton()
            }
        }

        private fun disablebutton(){
            for(i in board)
            {
                for(button in i )
                {
                    button.isEnabled = false
                }
            }
        }

        private fun updatevalue(row: Int,col: Int,player : Boolean){

            val text = if(player) "X" else "O"
            val value = if(player) 1 else 0

            board[row][col].apply {

                isEnabled=false
                setText(text)
            }

            boardstatus[row][col]=value
        }
    }




