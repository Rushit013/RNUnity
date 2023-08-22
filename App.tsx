import React from 'react';
import {
  SafeAreaView,
  StyleSheet,
  Text,
  TouchableOpacity,
  View,
  NativeModules,
  Platform,
  NativeEventEmitter,
} from 'react-native';

function App(): JSX.Element {
  const NativeEvents =
    Platform.OS === 'ios'
      ? new NativeEventEmitter(NativeModules.rnunitygames)
      : new NativeEventEmitter(NativeModules.Game_platform);

  return (
    <SafeAreaView style={styles.safeAreaContainer}>
      <View style={styles.rootContainer}>
        <TouchableOpacity
          style={styles.actionButton}
          onPress={() => {
            if (Platform.OS === 'ios') {
              NativeModules.rnunitygames.sendDataToUnity(JSON.stringify({}));
            } else {
              NativeModules.Game_platform.sendDataToUnity(JSON.stringify({}));
            }
          }}>
          <Text style={styles.buttonLabel}>Launch Game</Text>
        </TouchableOpacity>
      </View>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  safeAreaContainer: {
    flex: 1,
    width: '100%',
  },
  rootContainer: {
    flex: 1,
    width: '100%',
    padding: 12,
    alignItems: 'center',
    justifyContent: 'center',
  },
  actionButton: {
    height: 45,
    width: '100%',
    backgroundColor: 'black',
    alignItems: 'center',
    justifyContent: 'center',
  },
  buttonLabel: {
    textAlign: 'center',
    color: 'white',
  },
});

export default App;
