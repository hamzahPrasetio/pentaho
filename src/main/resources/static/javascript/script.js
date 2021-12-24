function onload() {
    addMonths();
    addYears();
    addFileCol();
}

function getInputForm(formId) {
    var form = document.querySelector('#' + formId);
    var inputForm = form.querySelectorAll('.form-control');
    var jenisLaporan = inputForm[0].value;
    var tahun = inputForm[1].value;
    var bulan = inputForm[2].value;
    alert("Jenis Laporan: " + jenisLaporan + "\nTahun: " + tahun + "\nBulan: " + bulan);

//    return jenisLaporan, tahun, bulan;
}

function addYears() {
    const startYear = 2000;
    const currentYear = new Date().getFullYear();

    for (let formNo = 0; formNo < 3; formNo++) {
        for (let i = startYear; i <= currentYear; i++) {
            const optionYear = document.createElement('option');
            optionYear.value = i;
            optionYear.innerHTML = i;
            document.getElementsByClassName('form-tahun')[formNo].appendChild(optionYear);
        }
    }
}

function addMonths() {
    const months = ["Januari", "Februari", "Maret", "April", "Mei", "Juni",
                    "Juli", "Agustus", "September", "Oktober", "November", "Desember"];

    for (let formNo = 0; formNo < 3; formNo++) {
        for (let i = 0; i < months.length; i++) {
            const optionMonth = document.createElement('option');
            optionMonth.value = months[i];
            optionMonth.innerHTML = months[i];
            document.getElementsByClassName('form-bulan')[formNo].appendChild(optionMonth);
        }
    }
}

function addFileCol() {
    for (let i = 0; i < 6; i++) {
        const fileDiv = document.createElement('div');

            fileDiv.className = 'col-xl-3 pl-0';

            //<img th:src="@{/images/file-logo.svg}" alt="Logo file">
            //<img src="../static/images/file-logo.svg" alt="Logo file">
            fileDiv.innerHTML = `
                <div class="file d-flex flex-wrap align-items-center">
                    <img th:src="@{/images/file-logo.svg}" alt="Logo file">
                    <span>SLIK_F01_202111</span>
                </div>
            `;

        document.getElementById('file-container').appendChild(fileDiv);
    }
}

function removeFileCol(input) {
    document.getElementById('file-container').removeChild(input.parentNode);
}