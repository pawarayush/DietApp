package com.example.ass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           DietSnapUI()

        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DietSnapUI() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("Today", style = MaterialTheme.typography.titleLarge)
                        Text("Thursday, 22nd Oct", style = MaterialTheme.typography.bodyMedium)
                    }
                },
                actions = {
                    IconButton(onClick = { /* Navigate to settings */ }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Settings")
                    }
                },
                colors = topAppBarColors(
                    containerColor = Color.White,
                    scrolledContainerColor = MaterialTheme.colorScheme.surface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    actionIconContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        bottomBar = {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.primary
    ) {
        NavigationBarItem(
            selected = currentTab == Tab.Activity,
            onClick = { currentTab = Tab.Activity },
           icon = { Icon(painterResource(R.drawable.ic_activity), contentDescription = "Activity") },
            label = { Text("Activity") }
        )
        NavigationBarItem(
            selected = currentTab == Tab.Goals,
            onClick = { currentTab = Tab.Goals },
            icon = { Icon(painterResource(R.drawable.ic_goals), contentDescription = "Goals") },
            label = { Text("Goals") }
        )
        NavigationBarItem(
            selected = currentTab == Tab.Camera,
            onClick = { currentTab = Tab.Camera },
           icon = { Icon(painterResource(R.drawable.ic_camera), contentDescription = "Camera") },
            label = { Text("Camera") }
        )
        NavigationBarItem(
            selected = currentTab == Tab.Feed,
            onClick = { currentTab = Tab.Feed },
            icon = { Icon(painterResource(R.drawable.ic_feed), contentDescription = "Feed") },
            label = { Text("Feed") }
        )
        NavigationBarItem(
            selected = currentTab == Tab.Profile,
            onClick = { currentTab = Tab.Profile },
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Profile") }
        )
    }
}
    ) { innerPadding ->
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text("SET GOAL!", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DietPointsCard(
                    title = "Diet Pts",
                    value = "1500",
                    color = Color(0xFFFFCDD2)
                )
                DietPointsCard(
                    title = "Exercise Pts",
                    value = "3/5",
                    color = Color(0xFFE1BEE7)
                )
                DietPointsCard(
                    title = "Health Score",
                    value = "88",
                    color = Color(0xFFD1C4E9)
                )
            }
        }

        item {
            Text("Your Goals", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        }
        item {
            GoalCard(
                image = painterResource(R.drawable.ic_img),
                title = "Keto Weight loss plan",
                subtitle = "Current Major Goal",
                progress = 0.73f
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Text("Explore", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        }
        item {
            ExploreOption(
                icon = painterResource(R.drawable.ic_find_diets),
                title = "Find Diets",
                description = "Find premade diets according to your cuisine"
            )
        }
        item {
            ExploreOption(
                icon = painterResource(R.drawable.ic_find_nutritionist),
                title = "Find Nutritionist",
                description = "Get customized diets to achieve your health goal"
            )
        }
    }
}
    }


enum class Tab {
    Activity, Goals, Camera, Feed, Profile
}

var currentTab = Tab.Activity

@Composable
fun DietPointsCard(
    title: String,
    value: String,
   color: Color
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(1f / 3f)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(color, shape = CircleShape)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
           Text(value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.White)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(title, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun GoalCard(
    image: Painter,
    title: String,
    subtitle: String,
    progress: Float
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Navigate to goal details */

            }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text(subtitle, style = MaterialTheme.typography.bodyMedium)
            }
        }
        CircularProgressIndicator(
            progress = { progress },
            modifier = Modifier.size(48.dp),
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = 4.dp,
        )
    }
}

@Composable
fun ExploreOption(
    icon: Painter,
    title: String,
    description: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Navigate to explore option */ }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(painter = icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text(description, style = MaterialTheme.typography.bodyMedium)
            }
        }
       Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
    }
}

@Composable
fun MyApp() {
    val json = """
        {
            "success": true,
            "data": {
                "section_1": {
                    "plan_name": "Chest Workout with Dumbbells & Barbell",
                    "progress": "35%"
                },
                "section_2": {
                    "accuracy": "82%",
                    "workout_duration": "30 mins",
                    "reps": 35,
                    "calories_burned": 12
                },
                "section_3": {
                    "plan_1": {
                        "plan_name": "Toned Arms for Women",
                        "difficulty": "Beginner"
                    },
                    "plan_2": {
                        "plan_name": "Toned Shoulders for Beginners",
                        "difficulty": "Beginner"
                    }
                },
                "section_4": {
                    "category_1": {
                        "category_name": "Triceps",
                        "no_of_exercises": "12"
                    },
                    "category_2": {
                        "category_name": "Biceps",
                        "no_of_exercises": "10"
                    }
                }
            },
            "message": "Homepage!"
        }
    """.trimIndent()

    val apiResponse = remember {
        Json.decodeFromString<ApiResponse>(json)
    }

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "Message: ${apiResponse.message}", style = MaterialTheme.typography.titleMedium)

                Section1UI(apiResponse.data.section1)
                Section2UI(apiResponse.data.section2)
                Section3UI(apiResponse.data.section3)
                Section4UI(apiResponse.data.section4)
            }
        }
    }
}

@Composable
fun Section1UI(section1: Section1) {
    Text(text = "Plan Name: ${section1.planName}", style = MaterialTheme.typography.bodyLarge)
    Text(text = "Progress: ${section1.progress}", style = MaterialTheme.typography.bodyLarge)
}

@Composable
fun Section2UI(section2: Section2) {
    Text(text = "Accuracy: ${section2.accuracy}", style = MaterialTheme.typography.bodyLarge)
    Text(text = "Workout Duration: ${section2.workoutDuration}", style = MaterialTheme.typography.bodyLarge)
    Text(text = "Reps: ${section2.reps}", style = MaterialTheme.typography.bodyLarge)
    Text(text = "Calories Burned: ${section2.caloriesBurned}", style = MaterialTheme.typography.bodyLarge)
}

@Composable
fun Section3UI(section3: Section3) {
    Text(text = "Plan 1: ${section3.plan_1.planName} (${section3.plan_1.difficulty})", style = MaterialTheme.typography.bodyLarge)
    Text(text = "Plan 2: ${section3.plan_2.planName} (${section3.plan_2.difficulty})", style = MaterialTheme.typography.bodyLarge)
}

@Composable
fun Section4UI(section4: Section4) {
    Text(text = "Category 1: ${section4.category_1.categoryName} (${section4.category_1.noOfExercises} exercises)", style = MaterialTheme.typography.bodyLarge)
    Text(text = "Category 2: ${section4.category_2.categoryName} (${section4.category_2.noOfExercises} exercises)", style = MaterialTheme.typography.bodyLarge)
}