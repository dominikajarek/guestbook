const form = document.querySelector("#form-input");
const container = document.querySelector(".container");
const button = document.querySelector("button");


(() => {
    loadEntries();
    form.addEventListener('submit', function (e) {
        e.preventDefault();

        let date = new Date();
        let day = String(date.getDate()).padStart(2, '0');
        let month = String(date.getMonth() + 1).padStart(2, '0'); //January is 0!
        let year = date.getFullYear();
        date = day + '/' + month + '/' + year;

        const entry = `name=${this.name.value}&comment=${this.comment.value}&email=${this.email.value}&date=${date}`;
        setEntry(entry);
    });
})();


function setEntry(data) {
    fetch("http://localhost:8008/guestbook",
        {
            mode: "no-cors",
            method: "POST",
            body: data
        })
        .then(function (response) {
            console.log(response);
        });
}

function loadEntries() {
    fetch("http://localhost:8008/guestbook")
        .then(function (response) {
            return response.json();
        })
        .then(function (entries) {
            displayEntries(entries);
        });
}

function displayEntries(entries) {
    entries.forEach(entry => {
        let node = `<div class="entry">
                            <p id="name">${entry.name}</p><br>
                            <p id="text">${entry.comment}</p><br>
                            <p id="email">${entry.email}</p><br>
                            <p id="date">${entry.date.dayOfMonth}/${entry.date.monthValue}/${entry.date.year}</p>
                    </div>`;
        container.innerHTML += node;
    });
}