package com.manriquetavi.bakeryapp.presentation.screens.track

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.manriquetavi.bakeryapp.R
import com.manriquetavi.bakeryapp.domain.model.FoodOrder
import com.manriquetavi.bakeryapp.domain.model.Order
import com.manriquetavi.bakeryapp.domain.model.Response
import com.manriquetavi.bakeryapp.presentation.components.ProgressBarCircular
import com.manriquetavi.bakeryapp.presentation.components.dialogs.AlertDialogCommon
import com.manriquetavi.bakeryapp.ui.theme.*
import com.manriquetavi.bakeryapp.util.Util
import java.text.DecimalFormat
import java.util.*

fun Context.getActivity(): AppCompatActivity? = when (this) {
    is AppCompatActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}

@Composable
fun TrackScreen(
    navController: NavHostController,
    trackViewModel: TrackViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val activity = LocalContext.current as Activity
    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value)
        AlertDialogCommon(
            showDialog = showDialog,
            title = "Title",
            text = "text tet text?"
        ) {
            if(ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.CALL_PHONE), 101)
            } else {
                makeACall(context, "+51947475537")
            }
        }
    Scaffold(
        topBar = {
            TrackTopBar(navController) {
                showDialog.value = true
            }
                 },
        backgroundColor = Color.Transparent
    ) { paddingValues ->
        when(val selectedOrder = trackViewModel.selectedOrder.value) {
            is Response.Loading -> ProgressBarCircular()
            is Response.Success -> selectedOrder.data?.let { TrackScreenContent(selectedOrder.data, paddingValues) }
            is Response.Error -> Util.printError(selectedOrder.message)
        }
    }
}

@Composable
fun TrackScreenContent(
    order: Order,
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (order.status != 0 || order.status!= 5) {
            Text(
                text = "Estimated time of delivery",
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.descriptionColor,
                fontWeight = FontWeight.Normal,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = calculateTimeEstimated(order.date!!.toDate()),
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.titleColor,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Image(
            modifier = Modifier
                .padding(vertical = 24.dp)
                .width(240.dp)
                .height(150.dp),
            painter = when(order.status) {
                1 -> painterResource(id = R.drawable.order_state_1)
                2 -> painterResource(id = R.drawable.order_state_2) //Accepted
                3 -> painterResource(id = R.drawable.order_state_3) //Prepared
                4 -> painterResource(id = R.drawable.order_state_4) //Driver received
                5 -> painterResource(id = R.drawable.order_state_5) //Arrived home client
                0 -> painterResource(id = R.drawable.order_state_cancel) //Order canceled
                else -> painterResource(id = R.drawable.order_state_cancel) //Unknown
            },
            contentDescription = "Image Order State",
            contentScale = ContentScale.Fit
        )
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            when(order.status) {
                1 -> {
                    LinearProgressIndicator(modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.weight(0.2f))
                    LinearProgressIndicator(modifier = Modifier.weight(2f), progress = 0f)
                    Spacer(modifier = Modifier.weight(0.2f))
                    LinearProgressIndicator(modifier = Modifier.weight(2f), progress = 0f)
                    Spacer(modifier = Modifier.weight(0.2f))
                    LinearProgressIndicator(modifier = Modifier.weight(1f), progress = 0f)
                }
                2 ->  {
                    LinearProgressIndicator(modifier = Modifier.weight(1f), progress = 1f)
                    Spacer(modifier = Modifier.weight(0.2f))
                    LinearProgressIndicator(modifier = Modifier.weight(2f))
                    Spacer(modifier = Modifier.weight(0.2f))
                    LinearProgressIndicator(modifier = Modifier.weight(2f), progress = 0f)
                    Spacer(modifier = Modifier.weight(0.2f))
                    LinearProgressIndicator(modifier = Modifier.weight(1f), progress = 0f)
                }
                3 -> {
                    LinearProgressIndicator(modifier = Modifier.weight(1f), progress = 1f)
                    Spacer(modifier = Modifier.weight(0.2f))
                    LinearProgressIndicator(modifier = Modifier.weight(2f), progress = 1f)
                    Spacer(modifier = Modifier.weight(0.2f))
                    LinearProgressIndicator(modifier = Modifier.weight(2f))
                    Spacer(modifier = Modifier.weight(0.2f))
                    LinearProgressIndicator(modifier = Modifier.weight(1f), progress = 0f)
                }
                4 -> {
                    LinearProgressIndicator(modifier = Modifier.weight(1f), progress = 1f)
                    Spacer(modifier = Modifier.weight(0.2f))
                    LinearProgressIndicator(modifier = Modifier.weight(2f), progress = 1f)
                    Spacer(modifier = Modifier.weight(0.2f))
                    LinearProgressIndicator(modifier = Modifier.weight(2f), progress = 1f)
                    Spacer(modifier = Modifier.weight(0.2f))
                    LinearProgressIndicator(modifier = Modifier.weight(1f))
                }
                5 -> {
                    LinearProgressIndicator(modifier = Modifier.weight(1f), progress = 1f)
                    Spacer(modifier = Modifier.weight(0.2f))
                    LinearProgressIndicator(modifier = Modifier.weight(2f), progress = 1f)
                    Spacer(modifier = Modifier.weight(0.2f))
                    LinearProgressIndicator(modifier = Modifier.weight(2f), progress = 1f)
                    Spacer(modifier = Modifier.weight(0.2f))
                    LinearProgressIndicator(modifier = Modifier.weight(1f), progress = 1f)
                }
                0 -> { Unit }
                else -> { Unit }
            }
        }
        Text(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            text = when(order.status) {
                1 -> "Esperando que el pedido sea aceptado."
                2 -> "Orden Aceptada, preparando la orden"
                3 -> "Motorizado recogiendo la orden en tienda."
                4 -> "Motorizado en camino."
                5 -> "Orden entregada al cliente."
                0 -> "Orden cancelada."
                else -> ""
            },
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.titleColor,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        DetailOrderCard(
            order = order
        )
    }
}

@Composable
fun DetailOrderCard(
    order: Order
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .padding(vertical = SMALL_PADDING)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(bottom = SMALL_PADDING)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.MenuBook ,
                        contentDescription = "Movie Image Icon",
                        tint = MaterialTheme.colors.primary
                    )
                    Text(
                        modifier = Modifier.padding(start = 20.dp),
                        text = "Order details",
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.titleColor,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                IconButton(
                    onClick = { expanded = !expanded }
                ) {
                    Icon(
                        imageVector = if(expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                        contentDescription = "Expand More Icon",
                        tint = MaterialTheme.colors.primary
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(bottom = SMALL_PADDING)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Order number",
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.descriptionColor,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = "#ASDF-#ASDF",
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.primary,
                    fontWeight = FontWeight.SemiBold
                )
            }
            AnimatedVisibility(visible = expanded) {
                Column {
                    order.foodOrders.values.forEach { foodOrder ->
                        FoodItemDetail(foodOrder)
                    }
                    Divider(modifier = Modifier.padding(horizontal = 4.dp))
                    Row(
                        modifier = Modifier
                            .padding(vertical = SMALL_PADDING)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Delivery:",
                            style = MaterialTheme.typography.caption
                        )
                        Text(
                            text = "5.00",
                            style = MaterialTheme.typography.caption
                        )
                    }
                }
            }
            Divider(modifier = Modifier.padding(horizontal = 4.dp))
            Row(
                modifier = Modifier
                    .padding(top = LARGE_PADDING)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total:",
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.titleColor,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "PEN ${order.totalPrice}",
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.titleColor,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun FoodItemDetail(
    foodOrder: FoodOrder
) {
    Row(
        modifier = Modifier
            .padding(vertical = SMALL_PADDING)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val totalPrice = foodOrder.quantity.times(foodOrder.unitPrice.toDouble())
        Row {
            Text(
                text = foodOrder.quantity.toString() + "x",
                style = MaterialTheme.typography.caption
            )
            Text(
                text = foodOrder.name,
                style = MaterialTheme.typography.caption
            )
        }
        Text(
            text = totalPrice.toString(),
            style = MaterialTheme.typography.caption
        )
    }
}

fun calculateTimeEstimated(date: Date): String {
    val timeSplit = date.toString().split(" ")[3].split(":")
    val hour = timeSplit[0].toInt()
    val minute = timeSplit[1].toInt()
    var hourEstimated1: Int = hour
    var minuteEstimated1: Int = minute + 30
    if (minuteEstimated1 > 60) {
        minuteEstimated1 -= 60
        hourEstimated1 += 1
    }
    var hourEstimated2: Int = hour
    var minuteEstimated2: Int = minute + 45
    if (minuteEstimated2 >60) {
        minuteEstimated2 -= 60
        hourEstimated2 += 1
    }
    return "${formatTwoDigits(hourEstimated1)}:${formatTwoDigits(minuteEstimated1)} - ${formatTwoDigits(hourEstimated2)}:${formatTwoDigits(minuteEstimated2)}"
}

fun formatTwoDigits(int: Int): String {
    return DecimalFormat("00").format(int)
}

fun makeACall(context: Context, phoneNumber: String) {
    val callIntent = Intent(Intent.ACTION_CALL)
    callIntent.data = Uri.parse("tel:$phoneNumber")
    startActivity(context, callIntent, bundleOf())
}

@Preview(showSystemUi = true)
@Composable
fun TrackScreenPreview() {
    TrackScreen(rememberNavController())
}