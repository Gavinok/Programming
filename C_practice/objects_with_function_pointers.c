#include <stdlib.h>
#include <stdio.h>

typedef struct Case Case;

Case *Case_create(double p);
double get_P(Case *self);
void Case_destroy(Case *self);

struct Case
{
    double p;
};

Case *Case_create(double p)
{
    Case *self = malloc(sizeof(*self));
    if (!self) return 0;
    self->p = p;
    return self;
}

double get_P(Case *self)
{
    double result = self->p;
    // calculate something
    return result;
}

void Case_destroy(Case *self)
{
    if (!self) return;
    free(self);
}

int main(void)
{
    Case *flip = Case_create(2.4);
    if (!flip) return EXIT_FAILURE;
    double result = get_P(flip);
    printf("OneParamDist_rvs(flip) = %lg\n", result);
    Case_destroy(flip);
    while(1);
}