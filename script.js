document.addEventListener("DOMContentLoaded", function () {
    const checkboxes = document.querySelectorAll(".stock-checkbox");
    const quantityDropdowns = document.querySelectorAll(".quantity-dropdown");
    const totalCapitalSpan = document.getElementById("totalCapital");

    function calculateCapital() {
        let totalCapital = 0;

        checkboxes.forEach((checkbox, index) => {
            if (checkbox.checked) {
                const price = parseFloat(checkbox.dataset.price);
                const quantity = parseInt(quantityDropdowns[index].value) || 0;
                totalCapital += price * quantity;
            }
        });

        totalCapitalSpan.textContent = `₹${totalCapital.toLocaleString()}`;
    }

    // Event Listeners for checkbox & quantity change
    checkboxes.forEach((checkbox, index) => {
        checkbox.addEventListener("change", calculateCapital);
        quantityDropdowns[index].addEventListener("change", calculateCapital);
    });

    // Event listener for dashboard button
    document.getElementById("dashboardButton").addEventListener("click", function () {
        window.location.href = "dashboard.html"; // Redirect to dashboard page
    });

    // Fetch stock data for the dashboard
    fetchStockData();
});

function fetchStockData() {
    fetch("http://localhost:8080/api/stocks")
        .then(response => response.json())
        .then(data => {
            document.getElementById("avg52wh").innerText = data.avg52wHigh;
            document.getElementById("avg52wl").innerText = data.avg52wLow;
            document.getElementById("closeTotal").innerText = data.closeTotal;
            document.getElementById("openTotal").innerText = data.openTotal;
            document.getElementById("volumeTotal").innerText = data.volumeTotal;

            drawCharts(data);
        });
}

function drawCharts(data) {
    const ctx = document.getElementById("dailyChart").getContext("2d");
    new Chart(ctx, {
        type: "line",
        data: {
            labels: data.dates,
            datasets: [
                {
                    label: "Sum of Close",
                    data: data.closeValues,
                    borderColor: "#f39c12",
                    fill: false
                },
                {
                    label: "Sum of Open",
                    data: data.openValues,
                    borderColor: "#e74c3c",
                    fill: false
                }
            ]
        }
    });

    const ctx2 = document.getElementById("volumeChart").getContext("2d");
    new Chart(ctx2, {
        type: "bar",
        data: {
            labels: data.months,
            datasets: [
                {
                    label: "Sum of Volume",
                    data: data.volumeValues,
                    backgroundColor: "#f1c40f"
                }
            ]
        }
    });
}
