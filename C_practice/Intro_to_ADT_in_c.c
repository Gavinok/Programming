#include <stdlib.h>
#include <stdio.h>

typedef struct Customer* CustomerPtr;

struct Customer
{
    const char* name;
    int address;
};

CustomerPtr createCustomer(const char* name, int address)
{
    CustomerPtr customer = malloc(sizeof* customer);
    if(customer)
    {
         /* Initialize each field in the customer... */ 
        customer->name = name;
        customer->address = address;
    }
    return customer;
}
void destroyCustomer(CustomerPtr customer)
{
     /* Perform clean-up of the customer internals, if necessary. */ 
    free(customer);
}
int main()
{
    printf("%d\n", 1);
    CustomerPtr Chris;
    int temp = 3;
    Chris = createCustomer("chris", temp);
    printf("%d\n", Chris->address);
    printf("%d\n", sizeof(Chris));
    struct Customer steve;
    printf("%d", sizeof(steve));
    destroyCustomer(Chris);
    return 0;
}