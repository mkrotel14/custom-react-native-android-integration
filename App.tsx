/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React, { useCallback, useMemo, useRef } from 'react';
import {
  View,
  Button,
  SafeAreaView,
  StatusBar,
  StyleSheet,
  useColorScheme,
  Text,
} from 'react-native';
import { BottomSheetModal, BottomSheetModalProvider } from '@gorhom/bottom-sheet';

import {
  Colors,
} from 'react-native/Libraries/NewAppScreen';



function App(): React.JSX.Element {
  const bottomSheetModalRef = useRef<BottomSheetModal>(null);

  const isDarkMode = useColorScheme() === 'dark';
  const snapPoints = useMemo(() => ['25%', '50%'], []);

  const handlePresentModalPress = useCallback(() => {
    bottomSheetModalRef.current?.present();
  }, []);
  const handleSheetChanges = useCallback((index: number) => {
    console.log('handleSheetChanges', index);
  }, []);

  const backgroundStyle = {
    backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
  };

  return (
    <BottomSheetModalProvider>
      <SafeAreaView style={backgroundStyle}>
        <StatusBar
          barStyle={isDarkMode ? 'light-content' : 'dark-content'}
          backgroundColor={backgroundStyle.backgroundColor}
        />
        <View style={styles.container}>
          <Button
            onPress={handlePresentModalPress}
            title="Present Modal"
            color="black"
          />
          <BottomSheetModal
            ref={bottomSheetModalRef}
            index={1}
            snapPoints={snapPoints}
            onChange={handleSheetChanges}
          >
            <View style={styles.contentContainer}>
              <Text>Awesome 🎉</Text>
            </View>
          </BottomSheetModal>
        </View>
      </SafeAreaView>
    </BottomSheetModalProvider>
    
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 24,
    justifyContent: 'center',
    backgroundColor: 'grey',
  },
  contentContainer: {
    flex: 1,
    alignItems: 'center',
  },
});

export default App;
