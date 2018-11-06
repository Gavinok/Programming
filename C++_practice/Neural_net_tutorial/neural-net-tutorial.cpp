/*
 * @CreateTime: Apr 26, 2018 8:37 PM
 * @Author: Gavin Jaeger-Freeborn
 * @Contact: gavinfre@uvic.ca
 * @Last Modified By: Gavin Jaeger-Freeborn
 * @Last Modified Time: Apr 26, 2018 9:51 PM
 * @Description: Modify Here, Please 
 */
#include <vector>
#include <iostream>// used for cout
#include <cstdlib>// used for random
#include <cassert>//adds assert function
using namespace std;

class Neuron;
typedef vector<Neuron> Layer;

/****************************class Neuron ****************/

class Neuron
{
    public:
        Neuron(unsigned numOutputs, unsigned myIndex);
        void setOutputVal(double val){ m_outputVal = val; }
        double getOutputVal(void) { return m_outputVal; }
        void feedForward(const Layer &prevLayer);
    private:
        static double transferFunction(double x);
        static double transferFunctionDerivitive(double x);
        double m_outputVal;
        vector<Connection> m_outputWeights;
        unsigned m_myIndex;
        /**
         * @brief returns a random value between 1 and 0
         * 
         * @return double random value between 1 and 0
         */
        static double randomWeight(void) { return rand() / double(RAND_MAX); } 
        
};
double Neuron::transferFunction(double x)
{

}
void Neuron::feedForward(const Layer &prevLayer)
{
    double sum = 0.0;
    /* sum the previous ouptuts layers outputs(which are our inputs)
    Include the bias node from the previouse layer. */
    for(unsigned n = 0; n < prevLayer.size(); ++n)
    {
        sum += prevLayer[n].getOutputVal() *
               prevLayer[n].m_outputWeights[m_myIndex].weight;
    }

    m_outputVal = Neuron::transferFunction(sum);
}
/**
 * @brief Construct a new Neuron object
 * 
 * @param numOutputs 
 */
Neuron::Neuron(unsigned numOutputs, unsigned myIndex)
{
    for(unsigned c = 0; c < numOutputs; ++c)
    {
        m_outputWeights.pop_back(Connection());
        //set the weight of this output to a random value
        m_outputWeights.back().weight = randomWeight(); 
    }
    m_myIndex = myIndex;
}

/****************************class Net*******************/
class Net
{
    public:
        Net(const vector<unsigned> &topology);
        void feedForward(const vector<double> &inputVals) {};
        void backProp(const vector<double> &targetVals) {};
        void getResults(const vector<double> &resultVals) const {};//should not change
    private:
        vector<Layer> m_layers; // m_layers[LayerNum][neuronNum]
};
void Net::feedForward(const vector<double> &inputVals) 
{
    //checks that a statment is true as part of debugging
    assert( inputVals.size() == m_layers[0].size() - 1);

    //Assign (latch) the input values into the input neurons
    for(unsigned i = 0; i< inputVals.size(); ++i)
    {
        m_layers[0][i].setOutputVal(inputVals[i]);
    }

    //Forward propogate aka please send the output to the next layer
    for(unsigned layerNum = 1; layerNum < m_layers.size(); ++layerNum)
    {
        Layer &prevLayer = m_layers[layerNum -1];// referance(&prevLayer) to previous layer
        //for each neuron in this layer
        for(unsigned n = 0; n < m_layers[layerNum].size() - 1; ++n)
        {
            m_layers[layerNum][n].feedForward(prevLayer);
        }
    }
};
/**
 * @brief Construct a new Net object
 * 
 * @param topology--the number of neurons in each layer
 */
Net::Net(const vector<unsigned> &topology)
{
    unsigned numLayers = topology.size();
    for(unsigned layerNum = 0; layerNum < numLayers; ++layerNum)
    {
        m_layers.push_back(Layer());
        /* if this is the final layer there are no outputs 
        else the number out outputs is the same as the number of neurons in the next layer */
        unsigned numOutputs = layerNum == topology.size - 1 ? 0 : topology[layerNum + 1]; 
        /*We have made a new layer, now fill it with neurons, and
        add a bias neuron to the layer (neuronNum <= topology[layerNum] adds the extra neuron)*/
        for(unsigned neuronNum = 0; neuronNum <= topology[layerNum]; ++neuronNum)
        {
            m_layers.back().push_back(Neuron(numOutputs, neuronNum));
            cout << "made a neuron!!" << endl;
        }
    }
}

int main()
{
    //eg {3, 2, 1} 3 input nodes
    vector<unsigned> topology;
    topology.push_back(3);
    topology.push_back(2);
    topology.push_back(1);
    Net myNet(topology);

    vector<double> inputVals;
    myNet.feedforward(inputVals);

    vector<double> targetVals;
    myNet.backProp(targetVals);

    vector<double> resultVals;
    myNet.getResults(resultVals);

}