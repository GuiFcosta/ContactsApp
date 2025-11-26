package pt.isec.amov.contacts.ui.screens

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.widget.ImageButton
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.FileProvider
import androidx.core.content.PermissionChecker
import androidx.core.content.PermissionChecker.checkSelfPermission
import coil3.compose.AsyncImage
import pt.isec.amov.contacts.ui.utils.FileUtils
import java.io.File

@Composable
fun EditScreen(
    name: MutableState<String>,
    email: MutableState<String>,
    phone: MutableState<String>,
    birthday: DatePickerState,
    picture: MutableState<String?>,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val pickImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            picture.value = uri?.let {
                FileUtils.createFileFromUri(context, it)
            }
        }
    )
    val imagePath = remember {FileUtils.getTempFilename(context)}
    val fileUri = remember {
        FileProvider.getUriForFile(
            context,
            "pt.isec.amov.contacts.android.fileprovider",
            File(imagePath)
        )
    }
    val takePicture = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) {
                picture.value = FileUtils.copyFile(context, imagePath)
            }
        }
    )
    val requestCameraPermission = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                takePicture.launch(fileUri)
            }
        }
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
    ) {
        Spacer(Modifier.height(16.dp))
        picture.value?.let { path ->
            AsyncImage(
                model = path,
                contentDescription = "Contact's picture",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth(0.35f)
                    .align(Alignment.CenterHorizontally)
                    .clip(CircleShape)
                    .aspectRatio(1f)
                    .clickable {
                        pickImage.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    }
            )
        } ?: Button(
            onClick = {
                when (PackageManager.PERMISSION_GRANTED) {
                    checkSelfPermission(context, Manifest.permission.CAMERA) -> {
                        takePicture.launch(fileUri)
                    }
                    else -> {
                        requestCameraPermission.launch(Manifest.permission.CAMERA)
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth(0.35f)
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Add Picture")
        }
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = name.value,
            isError = name.value.isEmpty(),
            label = {
                Text("Name:")
            },
            onValueChange = { newText ->
                name.value = newText
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = email.value,
            isError = email.value.isEmpty(),
            label = {
                Text("Email:")
            },
            onValueChange = { newText ->
                email.value = newText
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = phone.value,
            isError = phone.value.isEmpty(),
            label = {
                Text("Phone:")
            },
            onValueChange = { newText ->
                phone.value = newText
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        Text("Birthday:")
        DatePicker(
            state = birthday,
        )
    }
}