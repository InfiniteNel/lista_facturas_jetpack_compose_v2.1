package com.jroslar.listafacturasjetpackcomposev21.ui.screens.listafacturas

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.jroslar.listafacturasjetpackcomposev21.R
import com.jroslar.listafacturasjetpackcomposev21.core.Extensions.Companion.getResourceStringAndroid
import com.jroslar.listafacturasjetpackcomposev21.ui.theme.normalTextDialogFragment
import com.jroslar.listafacturasjetpackcomposev21.ui.theme.normalTextFragment
import com.jroslar.listafacturasjetpackcomposev21.ui.theme.titleFragment
import com.jroslar.listafacturasjetpackcomposev21.ui.viewmodel.listafacturas.FiltrarFacturasViewModel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FiltrarFacturasScreen(navController: NavController) {
    Scaffold(
        topBar = { Toolbar(navController) },
        content = { Content() }
    )
}

//@Preview
@Composable
private fun Toolbar(navController: NavController) {
    TopAppBar(
        title = { Text(text = "") },
        backgroundColor = Color.White,
        elevation = 0.dp,
        actions = {
            IconButton(onClick = {  }) {
                Icon(painterResource(id = R.drawable.close_icon), "")
            }
        }
    )
}

@Composable
private fun Content(viewModel: FiltrarFacturasViewModel = koinViewModel()) {
    val context = LocalContext.current
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (lazyColumFiltro, columFiltro) = createRefs()

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .constrainAs(lazyColumFiltro) {
                    top.linkTo(parent.top)
                    bottom.linkTo(columFiltro.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(20.dp)
        ) {
            item {
                Text(
                    text = R.string.tvTitleFiltrarFacturaText.getResourceStringAndroid(
                        context
                    ),
                    style = titleFragment
                )
                FiltrarPorFecha()
                FiltrarPorImporte()
                FiltrarPorEstado()
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(columFiltro) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .background(Color.White)
        ) {
            Button(
                onClick = {  },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 10.dp, top = 10.dp),
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = R.string.btAplicarFiltroFiltrarFacturaText.getResourceStringAndroid(context),
                    style = normalTextDialogFragment,
                    color = Color.White,
                    modifier = Modifier
                        .padding(horizontal = 80.dp, vertical = 5.dp)
                )
            }
            Button(
                onClick = {  },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 10.dp),
                shape = MaterialTheme.shapes.small,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
            ) {
                Text(
                    text = R.string.btEliminarFiltroFiltrarFacturaText.getResourceStringAndroid(context),
                    style = normalTextDialogFragment,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .padding(horizontal = 80.dp, vertical = 5.dp)
                )
            }
        }
    }
}

@Composable
private fun FiltrarPorEstado() {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = R.string.tvTitleEstadoFiltrarFacturaText.getResourceStringAndroid(context),
            style = normalTextFragment,
            fontWeight = FontWeight.Bold
        )
        CheckBoxStander(R.string.chPagadoFiltrarFacturaText.getResourceStringAndroid(context))
        CheckBoxStander(R.string.chAnuladasFiltrarFacturaText.getResourceStringAndroid(context))
        CheckBoxStander(R.string.chCuotaFijaFiltrarFacturaText.getResourceStringAndroid(context))
        CheckBoxStander(R.string.chPedientesDePagoFiltrarFacturaText.getResourceStringAndroid(context))
        CheckBoxStander(R.string.chPlanDePagoFiltrarFacturaText.getResourceStringAndroid(context))
    }
}

@Composable
private fun CheckBoxStander(text: String) {
    var checkBoxValue by remember { mutableStateOf(false) }
    Row() {
        Checkbox(
            checked = checkBoxValue,
            onCheckedChange = { checkBoxValue = it },
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colors.primary,
                checkmarkColor = MaterialTheme.colors.onPrimary,
                uncheckedColor = MaterialTheme.colors.onBackground.copy(alpha = 0.3f)
            )
        )
        Text(
            text = text,
            style = normalTextFragment,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FiltrarPorImporte() {
    val context = LocalContext.current
    var sliderPosition by remember { mutableStateOf(0f) }

    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
    ) {
        val (tvTitleImporte, slImporte, tvMaxValue, tvMinValue, tvRangeValue, dv) = createRefs()

        Text(
            text = R.string.tvTitleImporteFiltrarFacturaText.getResourceStringAndroid(context),
            style = normalTextFragment,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(tvTitleImporte) {
                top.linkTo(parent.top, margin = 10.dp)
                start.linkTo(parent.start)
            }
        )
        Text(
            text = "",
            style = normalTextFragment,
            modifier = Modifier.constrainAs(tvMinValue) {
                top.linkTo(tvTitleImporte.bottom, margin = 10.dp)
                start.linkTo(parent.start)
            }
        )
        Text(
            text = "",
            style = normalTextFragment,
            modifier = Modifier.constrainAs(tvRangeValue) {
                top.linkTo(tvTitleImporte.bottom, margin = 6.dp)
                end.linkTo(tvMaxValue.start)
                start.linkTo(tvMinValue.end)
            }
        )
        Text(
            text = "",
            style = normalTextFragment,
            modifier = Modifier.constrainAs(tvMaxValue) {
                top.linkTo(tvTitleImporte.bottom, margin = 10.dp)
                end.linkTo(parent.end)
            }
        )
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            modifier = Modifier
                .constrainAs(slImporte) {
                    top.linkTo(tvMinValue.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        Divider(
            modifier = Modifier
                .constrainAs(dv) {
                    start.linkTo(parent.start)
                    top.linkTo(slImporte.bottom)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .padding(vertical = 15.dp)
        )
    }
}

@Composable
private fun FiltrarPorFecha() {
    val context = LocalContext.current
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
    ) {
        val (tvTitleFecha, tvTextFechaDesde, tvTextFechaHasta, btFechaDesde, btFechaHasta, dv) = createRefs()

        Text(
            text = R.string.tvTitleFechaFiltrarFacturaText.getResourceStringAndroid(context),
            style = normalTextFragment,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(tvTitleFecha) {
                top.linkTo(parent.top, margin = 10.dp)
                start.linkTo(parent.start)
            }
        )
        Text(
            text = R.string.tvFechaDesdeFiltrarFacturaText.getResourceStringAndroid(context),
            style = normalTextFragment,
            color = Color.Gray,
            modifier = Modifier.constrainAs(tvTextFechaDesde) {
                top.linkTo(tvTitleFecha.bottom, margin = 15.dp)
                start.linkTo(parent.start)
            }
        )
        Button(
            onClick = {  },
            modifier = Modifier.constrainAs(btFechaDesde) {
                top.linkTo(tvTextFechaDesde.bottom, margin = 5.dp)
                start.linkTo(parent.start)
            },
            shape = MaterialTheme.shapes.large,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)
        ) {
            Text(
                text = R.string.btFechaFiltrarFacturaText.getResourceStringAndroid(context),
                style = normalTextFragment
            )
        }
        Text(
            text = R.string.tvFechaDesdeFiltrarFacturaText.getResourceStringAndroid(context),
            style = normalTextFragment,
            color = Color.Gray,
            modifier = Modifier.constrainAs(tvTextFechaHasta) {
                top.linkTo(tvTitleFecha.bottom, margin = 15.dp)
                start.linkTo(btFechaDesde.end, margin = 20.dp)
            }
        )
        Button(
            onClick = {  },
            modifier = Modifier.constrainAs(btFechaHasta) {
                top.linkTo(tvTextFechaHasta.bottom, margin = 5.dp)
                start.linkTo(btFechaDesde.end, margin = 20.dp)
            },
            shape = MaterialTheme.shapes.large,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)
        ) {
            Text(
                text = R.string.btFechaFiltrarFacturaText.getResourceStringAndroid(context),
                style = normalTextFragment
            )
        }
        Divider(
            modifier = Modifier
                .constrainAs(dv) {
                    start.linkTo(parent.start)
                    top.linkTo(btFechaDesde.bottom)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .padding(vertical = 15.dp)
        )
    }
}