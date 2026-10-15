import { Component, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  imports: [FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  sender = 'Alice';
  receiver = 'Bob';
  amount = 4500;

  status = signal('');
  reason = signal('');
  loading = signal(false);

  async checkTransaction() {
    this.loading.set(true);
    this.status.set('');
    this.reason.set('');

    try {
      const response = await fetch('/api/transactions/check', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          sender: this.sender,
          receiver: this.receiver,
          amount: this.amount
        })
      });

      const result = await response.json();
      this.status.set(result.status);
      this.reason.set(result.reason);
    } catch {
      this.status.set('ERROR');
      this.reason.set('Cannot connect to the backend');
    } finally {
      this.loading.set(false);
    }
  }
}
