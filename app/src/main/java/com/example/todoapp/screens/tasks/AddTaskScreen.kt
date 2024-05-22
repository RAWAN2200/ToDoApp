package com.example.todoapp.screens.tasks

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.todoapp.component.AddTagsListView
//import com.example.todoapp.component.AddTagsListView
import com.example.todoapp.component.CustomTextField
import com.example.todoapp.component.TasksHeaderView

import com.example.todoapp.data.Entity.Tags
import com.example.todoapp.getIconName
import com.example.todoapp.navigation.Screens
import com.example.todoapp.ui.theme.PrimaryColor
import java.util.Calendar


@Composable
fun AddTaskScreen(navController: NavHostController, addTask: AddTaskViewModel = viewModel()) {
    val list = listOf(
        Tags("Home", Color.Red.toArgb().toString() ,getIconName(Icons.Outlined.Home)),
        Tags("Work", Color.Blue.toArgb().toString(),getIconName(Icons.Outlined.AccountBox)),
        Tags("School", Color.Blue.toArgb().toString(),getIconName(Icons.Outlined.AddCircle)),
        Tags("Personal", Color.Yellow.toArgb().toString(),getIconName(Icons.Outlined.Done))
    )



    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    var selectedDate by remember { mutableStateOf("") }

    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, monthOfYear, dayOfMonth ->
            selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
            addTask.taskDate.value = selectedDate
        }, year, month, day
    )

    LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
        item {
            TasksHeaderView("Add Task")
        }

        item {
            CustomTextField(Modifier, "Title", Color.Gray, addTask.title)
            CustomTextField(Modifier, "Date", Color.Gray, addTask.taskDate, trailingIcon = {
                Icon(imageVector = Icons.Outlined.DateRange, contentDescription = null,
                    modifier = Modifier.clickable {
                        datePickerDialog.show()
                    })
            })
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CustomTextField(Modifier.weight(1f), "Time From", Color.Gray, addTask.startTime)
                CustomTextField(Modifier.weight(1f), "Time To", Color.Gray, addTask.endTime)
            }
            CustomTextField(Modifier, "Description", Color.Gray, addTask.description)
        }


        item {
            AddTagsListView(list) {
                addTask.category.value = it.name
            }
        }

        item {
            ButtonAddTask(addTask)
        }
    }
}

@Composable
fun ButtonAddTask(addTask: AddTaskViewModel) {
    Button(
        onClick = {
            addTask.addTask()
        },
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(22.dp)
            .padding(bottom = 100.dp)
            .semantics {
                testTag = "Add Task Button"
            },
        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryColor
        )
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp), text = "Create",
            fontSize = 16.sp,
            color = Color.White
        )
    }
}

