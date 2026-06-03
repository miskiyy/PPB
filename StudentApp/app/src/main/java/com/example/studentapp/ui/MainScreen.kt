package com.example.studentapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.data.Siswa
import com.example.studentapp.viewmodel.StudentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: StudentViewModel
) {
    var nama by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var editingSiswa by remember { mutableStateOf<Siswa?>(null) }
    
    val siswaList by viewModel.siswaList.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Registrasi Siswa",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                text = if (editingSiswa != null) "Edit Data Siswa" else "Tambah Siswa Baru",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = if (editingSiswa != null) "Perbarui nama dan email siswa" else "Kelola dan daftarkan data siswa baru",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            FormInput(
                nama = nama,
                email = email,
                onNamaChange = { nama = it },
                onEmailChange = { email = it },
                isEditMode = editingSiswa != null,
                onActionClick = {
                    if (nama.isBlank() || email.isBlank() || !email.contains("@")) {
                        return@FormInput
                    }
                    val currentEdit = editingSiswa
                    if (currentEdit != null) {
                        viewModel.editSiswa(
                            currentEdit.copy(nama = nama.trim(), email = email.trim())
                        )
                        editingSiswa = null
                    } else {
                        viewModel.tambahSiswa(nama.trim(), email.trim())
                    }
                    nama = ""
                    email = ""
                },
                onBatalClick = {
                    nama = ""
                    email = ""
                    editingSiswa = null
                }
            )

            Spacer(modifier = Modifier.height(24.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Daftar Siswa Terdaftar",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            if (siswaList.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "🌸",
                            fontSize = 48.sp,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = "Belum ada data siswa",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    items(siswaList, key = { it.id }) { siswa ->
                        StudentItem(
                            siswa = siswa,
                            onDelete = {
                                if (editingSiswa?.id == siswa.id) {
                                    editingSiswa = null
                                    nama = ""
                                    email = ""
                                }
                                viewModel.hapusSiswa(siswa)
                            },
                            onEdit = {
                                editingSiswa = siswa
                                nama = siswa.nama
                                email = siswa.email
                            }
                        )
                    }
                }
            }
        }
    }
}
