document.addEventListener('DOMContentLoaded', () => {
    const expenseForm = document.getElementById('expense-form');
    const expenseList = document.getElementById('expense-list');

    expenseForm.addEventListener('submit', (e) => {
        e.preventDefault();

        // Get form values
        const category = document.getElementById('category').value;
        const amount = document.getElementById('amount').value;
        const description = document.getElementById('description').value;

        // Create new list item
        const listItem = document.createElement('li');
        listItem.innerHTML = `<strong>${category}:</strong> ₹${amount} <span>${description}</span>`;

        // Append to expense list
        expenseList.appendChild(listItem);

        // Clear form inputs
        expenseForm.reset();
    });
});
