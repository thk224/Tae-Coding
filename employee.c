// Tae Kim(tk2686)
#include "employee.h"
EMPLOYEE *list_of_employees = NULL;

void printEmployee(EMPLOYEE e) {
  // Your code here
  printf("==================================\n");
  printf("Name: %s, ID: %d\n", e.name, e.id); // print name and id
  printf("----------------------------------\n");
  if (e.payments == NULL) {
    printf("No Payments.\n"); // if there is no payments
    printf("----------------------------------\n");
    return;
  }
  printf("*Payments*\n");
  while (e.payments != NULL) { // print every payments
    printf("Date: %s, Amount: %f\n", e.payments->date, e.payments->amount);
    e.payments = e.payments->next;
  }
  printf("==================================\n");
}

void addEmployee(int e_id, char *e_name) {
  // Your code here
  EMPLOYEE *e;
  e = malloc(sizeof(EMPLOYEE));
  e->id = e_id;
  e->name = e_name;
  if (list_of_employees == NULL) { // if list is empty
    list_of_employees = e;
  } else { // insert at the begining of the list
    e->next = list_of_employees;
    list_of_employees = e;
  }
}

void printEmployees() {
  // Your code here
  EMPLOYEE *ptr = list_of_employees;
  if (ptr == NULL) {
    printf("No Employees\n");
    return;
  }
  while (ptr != NULL) { // print every employees
    printEmployee(*ptr);
    ptr = ptr->next;
  }
}

int addPayment(int e_id, char *p_date, float p_amount) {
  // Your code here
  EMPLOYEE *ptr = list_of_employees;
  if (ptr == NULL) {
    return 0; // fail
  }
  while (ptr != NULL) {
    if (ptr->id == e_id) {
      PAYMENT *p;
      p = malloc(sizeof(PAYMENT));
      p->date = p_date;
      p->amount = p_amount;
      // insert payment at the begining
      p->next = ptr->payments;
      ptr->payments = p;
      return 1; // success
    }
    ptr = ptr->next;
  }
  return 0; // fail
}

int deleteEmployee(int e_id, char *e_name) {
  // Your code here
  EMPLOYEE *cur = list_of_employees;
  EMPLOYEE *prev = NULL;
  if (cur == NULL) {
    return 0; // fail
  }
  if (cur->id == e_id) { // if need to delete the first employee
    list_of_employees = cur->next;
    return 1; // success
  }
  while (cur != NULL) {
    if (cur->id == e_id) {
      prev->next = cur->next;
      return 1; // success
    }
    prev = cur;
    cur = cur->next;
  }
  return 0; // fail
}