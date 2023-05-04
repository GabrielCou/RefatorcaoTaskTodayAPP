package com.example.tasktodayapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasktodayapp.model.Tarefa.Tarefa
import com.example.tasktodayapp.ui.theme.TaskTodayAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreenContent()
            }
        }
    }

@Composable
fun MainScreenContent() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,

        topBar = {
            TopAppBar(

                title = { Text(text = "TaskTodayApp",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,)},
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch{
                            scaffoldState.drawerState.open()
                        }
                    } ){
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Drawer Menu",
                            tint = Color.Black,

                        )
                    }
                },

                backgroundColor = Color.DarkGray
            )


        },

        drawerBackgroundColor = Color.Gray,
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,

        drawerContent = {
            Box(
                modifier = Modifier
                    .height(16.dp)
            ){
                    Text(text = "Opções!!!",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,)
            }

            Column(){
                Text(text = "Opção de Tarefas",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,)
                Text(text = "Opção de Notificações",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,)
                Text(text = "Opção X",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,)
            }


        },

        content = {

            paddingValues -> Log.i("paddinVales", "$paddingValues")
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize()
            ) {
                MySearchField(modificador = Modifier.fillMaxWidth())


                val tProvaDeCalculo = Tarefa(
                    "Estudar Prova de Calculo",
                    "Cplto1 do livro xyz",
                    Date(),
                    Date(),
                )

                val tProvaDeKotlin = Tarefa(
                    "Estudar Prova de Kotlin",
                    "Cplto1 do livro xyz",
                    Date(),
                    Date(),
                )

                var minhaListaDeTarefas = listOf<Tarefa>(tProvaDeCalculo, tProvaDeCalculo)



                MyTasksWidget(minhaListaDeTarefas)

            }
        },

        bottomBar = {

                BottomAppBar(
                    content = {Text("BottomBar",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,)},
                        backgroundColor = Color.DarkGray
                )
        },

        isFloatingActionButtonDocked = false,
        floatingActionButton = { ExtendedFloatingActionButton(
            icon = {
                   Icon(imageVector = Icons.Default.AddCircle ,
                       contentDescription = "Add Task" )
            },
            text = { Text(text = "ADD") },
            onClick = { /*TODO*/ }) }

    )

    }

@Composable
fun MyTasksWidget(listaDeTarefas: List<Tarefa>){

    listaDeTarefas.forEach(
        action = { MyTaskWidget(modificador = Modifier.fillMaxWidth(), tarefaASerMostrada = it ) }
    )

}

@Composable
fun MySearchField(modificador: Modifier){
    TextField(
        value = "",
        onValueChange = {},
        modifier = modificador,
        placeholder = { Text(text = "Pesquisar tarefas")},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color.Black,
            )
        }
    )
}


@Composable
fun MyTaskWidget(
        modificador: Modifier,
        tarefaASerMostrada: Tarefa

    ){
    val dateFormatter = SimpleDateFormat("EEE, MMM dd, yyyy", Locale.getDefault())
    Row(modifier = modificador) {
        Icon(
            imageVector = Icons.Default.Notifications,
            tint = Color.Black,
            contentDescription = "Icons  of a pendent taks"
        )

        Text(
            text = dateFormatter.format(tarefaASerMostrada.pzoFinal),
            fontSize = 12.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )

        Column(
            modifier = modificador
                .border(width = 1.dp, color = Color.Black)
                .padding(3.dp)
                .background(Color.White)

        ) {
            Text(
                text = tarefaASerMostrada.nome,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = Color.Black
            )
            Text(
                text = tarefaASerMostrada.detalhes,
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal
            )
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}
//@Preview(showBackground = true)

